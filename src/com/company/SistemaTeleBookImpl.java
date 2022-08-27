package com.company;

import ucn.ArchivoSalida;
import ucn.Registro;
import ucn.ArchivoEntrada;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class SistemaTeleBookImpl implements SistemaTeleBook {
    Scanner scan = new Scanner(System.in);
    String opcion1, opcion2, opcion3,opcion4, rutLogin, passwordLogin = "";
    boolean acceso = false;
    private ListaUsuario listaUsuario;
    int id=0;
    String superPostMasReciente = "";
    int cantidadDeSuperpostCreados = 0;

    private ListaSeguidores listaSeguidores;
    private ListaSeguidos listaSeguidos;
    private ListaPost listaPost;



    public SistemaTeleBookImpl() {
        listaUsuario = new ListaUsuario(50);
        //crear listas
    }

    /**
     * Este metodo sirve para ininiciar la lectura de los 2 .txt que se le entregaran,
     * eliminara los espacios que se encuentren en el entre las comillas para facilitar
     * la manipulacion en el programa
     */
    public void lecturaSeArchivos() throws IOException {

        ArchivoEntrada archivo = new ArchivoEntrada("usuarios.txt");
        while (!archivo.isEndFile()) {
            //lectura de usuarios
            Registro registro = archivo.getRegistro();

            String nombree = registro.getString();
            String apellidoo = registro.getString();
            String emaill = registro.getString();
            String nicknamee = registro.getString();
            String passwordd = registro.getString();
            String tipoDeUsuarioo = registro.getString();
            String seguidoss = registro.getString();
            String seguidoress = registro.getString();

            String nombre = nombree.replace(" ", "");
            String apellido = apellidoo.replace(" ", "");
            String email = emaill.replace(" ", "");
            String nickname = nicknamee.replace(" ", "");
            String password = passwordd.replace(" ", "");
            String tipoDeUsuario = tipoDeUsuarioo.replace(" ", "");
            int seguidos = Integer.parseInt(seguidoss.replace(" ", ""));
            int seguidores = Integer.parseInt(seguidoress.replace(" ", ""));


            if (tipoDeUsuario.equalsIgnoreCase("Administrador")) {
                agregarUsuarioAdminLecturaInicial(nombre, apellido, email, nickname, password, true, seguidores, seguidos);
            } else {
                agregarUsuarioNormalLecturaInicial(nombre, apellido, email, nickname, password, false, seguidores, seguidos);
            }

            // System.out.println(listaUsuario.buscarUsuarioNickname(nickname).getNickname());
            // System.out.println(listaUsuario.buscarUsuarioNickname(nickname).getPassword());






            }


            //lectura de seguidores

            ArchivoEntrada archivo2 = new ArchivoEntrada("usuarios_seguidos.txt");

            while (!archivo2.isEndFile()) {

                Registro registro2 = archivo2.getRegistro();

                String nicknameTratadoo = registro2.getString();
                String seguidoresTratadoo = registro2.getString();

                String nicknameTratado = nicknameTratadoo.replace(" ", "");//usuario nickame
                int seguidoresTratado = Integer.parseInt(seguidoresTratadoo.replace(" ", ""));


                for (int i = 0; i < seguidoresTratado; i++) {
                    Registro registro3 = archivo2.getRegistro();

                    String seguidoresNombree = registro3.getString();
                    String seguidoresNicknamee = registro3.getString();

                    String seguidoresNombre = seguidoresNombree.replace(" ", "");//seguidor de usuario nombre
                    String seguidoresNickname = seguidoresNicknamee.replace(" ", "");//seguidor de usuario nickname

                    listaUsuario.buscarUsuarioNickname(nicknameTratado).getListaSeguidores().agregarSeguidores(listaUsuario.buscarUsuarioNickname(seguidoresNickname));//agregamos seguidores a usuario
                    listaUsuario.buscarUsuarioNickname(seguidoresNickname).getListaSeguidos().agregarSeguidos(listaUsuario.buscarUsuarioNickname(nicknameTratado));//agregamos seguidos a usuario


                }

                System.out.println(" ");
            }

    }


    /**
     * Este metodo agregara los ucuarios que sean usuarios default que vengan de los archivos .txt
     * pero solo servira para esto yq que los .txt contienen valores de seguidres y
     * seguidos
     * @param nombre
     * @param apellido
     * @param email
     * @param nickname
     * @param password
     * @param esAdmin
     * @param seguidores
     * @param seguidos
     */
    public void agregarUsuarioNormalLecturaInicial(String nombre, String apellido , String email, String nickname, String password, boolean esAdmin,int seguidores,int seguidos){
        UsuarioNormal nuevoUsuario = new UsuarioNormal(nombre,apellido,email,nickname,password,false,seguidores,seguidos);
        listaUsuario.agregarUsuario(nuevoUsuario);

    }

    /**
     * Este metodo agregara los ucuarios que sean usuarios Admin que vengan de los archivos .txt
     * pero solo servira para esto yq que los .txt contienen valores de seguidres y
     * seguidos
     *
     * @param nombre
     * @param apellido
     * @param email
     * @param nickname
     * @param password
     * @param esAdmin
     * @param seguidores
     * @param seguidos
     */
    public void agregarUsuarioAdminLecturaInicial(String nombre, String apellido , String email, String nickname, String password, boolean esAdmin,int seguidores,int seguidos){
        Admin nuevoAdmin = new Admin(nombre,apellido,email,nickname,password,true,seguidores,seguidos);
        listaUsuario.agregarUsuario(nuevoAdmin);

    }

    /**
     * este metodo es el que inicia el sistema desde el main , y que esta conectado con el resto de la logica
     * @throws IOException
     */
    public void iniciarSistema() throws IOException {
        lecturaSeArchivos();

        do{
            listaUsuario.desplegarUsuarios();
            System.out.println("\n======================= TeleBook =======================\n"+
                    "\n Escoge una opcion que desea realizar:"+
                    "\n [1] iniciar sesion"+
                    "\n [0] cerrar aplicacion");

            opcion1 = scan.next();

            switch (opcion1){
                case "1":{
                    System.out.print("Ingrese nickname: ");
                    String nicknameLogeado = scan.next();
                    System.out.print("Ingrese contraseña: ");
                    String passwordLogeado = scan.next();

                    iniciarSesion(nicknameLogeado,passwordLogeado);
                    break;
                }
                case "0":{
                    System.out.println("================= Cerrando aplicacion ==================");
                    cerrarAplicacion();
                    break;

                }
            }
        }while(!opcion1.equals("0"));
    }//listo

    /**
     * Este método sirve para iniciar sesión,
     * recibe un Nickname y una contraseña luego corrobora
     * si está el Nickname en la lista de Usuarios, si el Nickname
     * EXISTE, corrobora si la contraseña es igual a la
     * que está ligada a ese Nickname si una de estas no permitira el
     * inicio de sesion
     * @param nicknameLogeado
     * @param passwordLogeado
     * @return
     */
    @Override
    public boolean iniciarSesion(String nicknameLogeado, String passwordLogeado) {
        acceso = false;

        System.out.println("\n===================== Iniciando sesión =====================\n");

        if(listaUsuario.buscarUsuarioNickname(nicknameLogeado) != null){
            if(listaUsuario.buscarUsuarioNickname(nicknameLogeado).getPassword().equals(passwordLogeado)){
                System.out.println("Sesion iniciada correctamente");
                acceso = true;
            }
        }else{

            System.out.println("Usuario no encontrado\n");
            return false;
        }


        if(acceso == true){//la sesion se inicio correctamente

            do{


                System.out.println("\n===================== Sesión iniciada ======================\n"
                        + "\n -[1] Crear post"
                        + "\n -[2] Perfil"
                        + "\n -[3] Ver post"
                        + "\n -[4] Estadísticas"
                        + "\n -[5] Registrar usuarios"
                        + "\n -[0] Cerrar sesión");

                opcion2 = scan.next();

                switch (opcion2){
                    case "1":{
                        System.out.println("================== Crear post ==================");
                        crearPost(nicknameLogeado);
                        break;
                    }

                    case "2":{//ver perfil

                        do {
                            System.out.println("\n===================== Perfil usuario ======================\n"
                                    + "\n -[1] Chequear perfil"
                                    + "\n -[2] Seguir usuario"
                                    + "\n -[3] Dejar de Seguir a usuario"
                                    + "\n -[4] Actualizar Datos"
                                    + "\n -[5] Cambiar contraseña"
                                    + "\n -[0] volver a menu anterior");

                            opcion3 = scan.next();

                            switch (opcion3) {
                                case "1": {
                                    System.out.println("=========================== Chequeando perfil ===========================");
                                    chequearPerfil(nicknameLogeado);
                                    break;
                                }

                                case "2": {
                                    System.out.println("======================== Seguir usuario ========================");
                                    seguirUsuario(nicknameLogeado);
                                    break;
                                }

                                case "3": {
                                    System.out.println("======================== Dejar de Seguir a usuario ========================");
                                    dejarDeSeguirUsuario(nicknameLogeado);
                                    break;
                                }

                                case "4": {
                                    System.out.println("======================== Actualizar Datos ========================");


                                    System.out.println("Que datos desea actualizar:"
                                            + "\n [1] Nombre"
                                            + "\n [2] Apellido"
                                            + "\n [3] Nickname"
                                            + "\n [4] Correo"
                                            + "\n [0] Salir");
                                    String opcion = scan.next();

                                    switch (opcion){
                                        case "1":{
                                            System.out.print("Ingrese el nombre nuevo al que quiere cambiar: ");
                                            listaUsuario.buscarUsuarioNickname(nicknameLogeado).setNombre(scan.next());

                                            break;
                                        }
                                        case "2":{
                                            System.out.print("Ingrese el apellido nuevo al que quiere cambiar: ");
                                            listaUsuario.buscarUsuarioNickname(nicknameLogeado).setApellido(scan.next());


                                            break;
                                        }
                                        case "3":{
                                            System.out.print("Ingrese el nickname nuevo al que quire cambiar: ");
                                            String nickname = scan.next();

                                            if(listaUsuario.buscarnickname(nickname)==true){
                                                System.out.println("El nickname ingresado ya existe");
                                            }else{
                                                listaUsuario.buscarUsuarioNickname(nicknameLogeado).setNickname(nickname);
                                                nicknameLogeado = nickname;
                                                System.out.println("nickname cambiado a "+nickname);
                                            }

                                            break;
                                        }


                                        case "4":{
                                            System.out.print("Ingrese el correo nuevo al que desea cambiar: ");
                                            String correo = scan.next();

                                            if(listaUsuario.buscarCorreo(correo)==true){
                                                System.out.println("El correo ingresado ya existe");
                                            }else{
                                                listaUsuario.buscarUsuarioNickname(nicknameLogeado).setEmail(correo);
                                                System.out.println("correo cambiado a "+correo);
                                            }

                                            break;
                                        }
                                        default:{
                                            break;
                                        }
                                    }


                                    //nicknameLogeado = actualizarDatos(nicknameLogeado);
                                    break;
                                }

                                case "5": {
                                    System.out.println("======================== Cambiando contraseña ========================\n");
                                    cambiarContrasena(nicknameLogeado);
                                    break;
                                }
                            }

                        } while (!opcion3.equals("0"));//mientras el valor no sea erroneo



                        //perfil(nicknameLogeado);
                        break;
                    }

                    case "3":{//ver posts






                        System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        if(superPostMasReciente.equalsIgnoreCase("")){
                            System.out.println("Aun no se publican SuperPost");
                        }else{
                            listaUsuario.buscarUsuarioNickname(superPostMasReciente).getListaPost().desplegarPostMasResiente();
                        }
                        System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

                        do{

                            System.out.println("\n================== Ver posts ==================\n\n" +
                                    "Escoge una opcion que desea realizar:"+
                                    "\n [1] ver post Seguidores"+
                                    "\n [2] ver post seguidos"+
                                    "\n [0] Volver al menu anterior");
                            opcion4 = scan.next();

                            switch (opcion4) {
                                case "1": {
                                    verPostSeguidores(nicknameLogeado);
                                    break;
                                }
                                case "2": {
                                    verPostSeguidos(nicknameLogeado);
                                    break;
                                }
                            }
                        }while(!opcion4.equals("0"));


                        //verPost(nicknameLogeado);
                        break;
                    }

                    case "4":{
                        System.out.println("================== estadisticas ==================\n");
                        usuarioMenor();
                        usuarioMayor();
                        seguidoresPromedio();
                        cantSuperPosts();
                        break;
                    }

                    case "5":{
                        System.out.println("================== Creando Usuario ==================");
                        if(listaUsuario.buscarUsuarioNickname(nicknameLogeado).getEsAdmin()== true){
                            registrarUsuario();
                        }else{
                            System.out.println("El usuario no es administrador, ");
                        }
                        break;
                    }
                }

            }while (!opcion2.equals("0"));//mientras el valor no sea erroneo

        }else{//si los datos de inicio de sesion son erroneos
            System.out.println("Contraseña incorrecta\n");
            return false;
        }

        System.out.println("Cerrando sesion ");

        return false;
    }//listo


    /**
     * Este metodo recibe el Nickname con el cual se logeo a la sesion, para permitirle al usuario
     * crear un Post desde teclado que variara si es o no Admin, y luego se agregara este post en el usuario en una
     * listaPost
     * @param nicknameLogeado
     */
    @Override
    public void crearPost(String nicknameLogeado) {
        LocalDate fechaLocal = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        System.out.println("Ingrese el contenido de tu post");
        scan.nextLine();
        String contenidoo = scan.nextLine();
        id = id+1;


        if(listaUsuario.buscarUsuarioNickname(nicknameLogeado).getEsAdmin()==true){
            String contenido = (

                    "\n********************************************"
                    +"\n--------------------------------------------"
                    +"\nN°"+id
                    +"\nAutor: "+nicknameLogeado
                    +"\nTipo de post: SuperPost\n\n"
                    +contenidoo
                    +"\n\nFecha: "+fechaLocal.format(formato)
                    +"\n--------------------------------------------"
                    +"\n********************************************\n");

            Post nuevoPost = new Post(nicknameLogeado,contenido);
            listaUsuario.buscarUsuarioNickname(nicknameLogeado).getListaPost().agregarPost(nuevoPost);
            superPostMasReciente = nicknameLogeado;
            cantidadDeSuperpostCreados++;

        }else{
            String contenido = (

                    "\n********************************************"
                    +"\n--------------------------------------------"
                    +"\nN°"+id
                    +"\nAutor: "+nicknameLogeado
                    +"\nTipo de post: Normal\n\n"
                    +contenidoo
                    +"\n\nFecha: "+fechaLocal.format(formato)
                    +"\n--------------------------------------------"
                    +"\n********************************************\n");

            Post nuevoPost = new Post(nicknameLogeado,contenido);
            listaUsuario.buscarUsuarioNickname(nicknameLogeado).getListaPost().agregarPost(nuevoPost);
        }
    }


    /**
     * El sistema desplegara la informacion asociada al usuario logeado que recivira
     * como parametro que sera
     * Nombre, Apellido, Seguidos, Seguidores, Correo, y el seguidor mas reciente
     * @param nicknameLogeado
     */
    @Override
    public void chequearPerfil(String nicknameLogeado) {

        System.out.println(
                "\n Nombre: "+listaUsuario.buscarUsuarioNickname(nicknameLogeado).getNombre()
                        +"\n Apellidos: "+listaUsuario.buscarUsuarioNickname(nicknameLogeado).getApellido()
                        +"\n Nickname: "+listaUsuario.buscarUsuarioNickname(nicknameLogeado).getNickname()
                        +"\n Seguidos: "+listaUsuario.buscarUsuarioNickname(nicknameLogeado).getListaSeguidos().getCantActualSeguidos()
                        +"\n Seguidores: "+listaUsuario.buscarUsuarioNickname(nicknameLogeado).getListaSeguidores().getCantActualSeguidores()
                        +"\n correo: "+listaUsuario.buscarUsuarioNickname(nicknameLogeado).getEmail()
                        +"\n Seguidor mas reciente: "+listaUsuario.buscarUsuarioNickname(nicknameLogeado).getListaSeguidores().getSeguidorMasReciente());
        System.out.print("Ingrese cualquier cosa para continuar...");
        scan.next();

    }


    /**
     * recivira como parametro el nickname con el que se loggeo el usuario para que el sistema luego
     * desplegara los usuarios registrados en sistema luego le solicite desde teclado a que usuario
     * desea segir, de ser el nickname correcto el usuario seguira al usuario, de no ser correcto el nickname
     * @param nicknameLogeado
     * @return
     */
    @Override
    public boolean seguirUsuario(String nicknameLogeado) {

        System.out.println("Ingresa el nickname de quien quieres a empezar a seguir:");
        listaUsuario.desplegarUsuarios();

        String usuarioASeguir = scan.next();

        try{
            listaUsuario.buscarUsuarioNickname(usuarioASeguir).getListaSeguidores().agregarSeguidores(listaUsuario.buscarUsuarioNickname(nicknameLogeado));
            listaUsuario.buscarUsuarioNickname(nicknameLogeado).getListaSeguidos().agregarSeguidos(listaUsuario.buscarUsuarioNickname(usuarioASeguir));
        }catch(Exception e){
            System.out.println("El usuario no existe en la lista");

        }

        return false;
    }


    /**
     * recivira como marametro el nickname con el que se inicio sesion, luego desplegara a todods los
     * nicknames de los usuarios que este esta siguiendo y le permitira ingresar desde teclado
     * el nickname a cual de estos desea dejar de seguir, si el usuario existe en su lista de seguidos
     * el usuario dejara de seguir al usuario, de lo contrario no lo dejara
     * @param nicknameLogeado
     * @return
     */
    @Override
    public boolean dejarDeSeguirUsuario(String nicknameLogeado) {
        System.out.println("Ingresa el Nickname del usuario que quieres dejar de seguir: ");
        listaUsuario.buscarUsuarioNickname(nicknameLogeado).getListaSeguidos().desplegarSeguidos();

        String dejarDeSeguirUsuario = scan.next();

        System.out.println("eliminando seguidos");
        if(listaUsuario.buscarUsuarioNickname(nicknameLogeado).getListaSeguidos().buscarSiExisteONo(dejarDeSeguirUsuario)){
            listaUsuario.buscarUsuarioNickname(nicknameLogeado).getListaSeguidos().eliminarSeguidos(dejarDeSeguirUsuario);
            listaUsuario.buscarUsuarioNickname(dejarDeSeguirUsuario).getListaSeguidores().eliminarSeguidores(nicknameLogeado);
        }


        return false;
    }

    /**
     * Esta funcion permite al usuario cambiar su contraseña, recivira como parametro el nickname con
     * el que se inicio sesion, primero le pedira desde teclado a cual contraseña desea cambiar, luego corroborara
     * si la contraseña tiene un tamaño mayor a 6 caracteres, luego corroborara si es que tiene almenos una mayuscula
     * finalmente luego que la contraseña no sea igual a la actual
     * de cumplir todos los requirimientos el sistema cambiara la contraseña
     * @param nicknameLogeado
     * @return
     */
    @Override
    public boolean cambiarContrasena(String nicknameLogeado) {

        System.out.print("Cambiar contraseña: ");
        String nuevaContraseña = scan.next();

        boolean mayuscula = false;

        for (int i = 0; i < nuevaContraseña.length(); i++) {
            if(Character.isUpperCase(nuevaContraseña.charAt(i))) {
                mayuscula = true;
            }
            if(nuevaContraseña.equalsIgnoreCase(listaUsuario.buscarUsuarioNickname(nicknameLogeado).getPassword())){
                mayuscula = false;

            }
        }


        if(nuevaContraseña.length()>6 && mayuscula == true && !listaUsuario.buscarUsuarioNickname(nicknameLogeado).getPassword().equalsIgnoreCase(nuevaContraseña) ){
            listaUsuario.buscarUsuarioNickname(nicknameLogeado).setPassword(nuevaContraseña);
            System.out.println("Contraseña cambiada con exito a "+nuevaContraseña);
        }else{
            System.out.println("La contraseña no requiere con los requisitos minimos \nno puede ser igual a la primera\n6 caracteres de longitud\n1 mayúscula en su composición");
        }

        return false;
    }


    /**
     * Esta funcuin recibira como parametro el nickname con el que se inicio sesion,
     * Luego desplegara el ultimo post realizado de cada usuario que sea seguidor del usuario logeado
     * @param nicknameLogeado
     */
    @Override
    public void verPostSeguidores(String nicknameLogeado) {

        for (int i = 0; i < listaUsuario.buscarUsuarioNickname(nicknameLogeado).getListaSeguidores().getCantActualSeguidores(); i++) {
            listaUsuario.buscarUsuarioNickname(nicknameLogeado).getListaSeguidores().getVecSeguidores()[i].getListaPost().desplegarPostMasResiente();
        }

    }

    /**
     * Esta funcuin recibira como parametro el nickname con el que se inicio sesion,
     * Luego desplegara el ultimo post realizado de cada usuario que sigua el usuario logeado
     * @param nicknameLogeado
     */
    @Override
    public void verPostSeguidos(String nicknameLogeado) {
        for (int i = 0; i < listaUsuario.buscarUsuarioNickname(nicknameLogeado).getListaSeguidos().getCantActualSeguidos(); i++) {
            listaUsuario.buscarUsuarioNickname(nicknameLogeado).getListaSeguidos().getVecSeguidos()[i].getListaPost().desplegarPostMasResiente();
        }
    }

    /**
     * Esta funcion deplegara la cantidad promedio de seguidores por usuario
     */
    @Override
    public void seguidoresPromedio() {

        double seguidoresTotales=0;
        double usuariosTotales = listaUsuario.getCantActual();

        if(listaUsuario.getCantActual()== 0){//si no hay usuarios registrados
            System.out.println("aun no existen usuarios");
        }else{
            for (int i = 0; i < listaUsuario.getCantActual(); i++) {//calculamos los seguidores totales
                seguidoresTotales = listaUsuario.getVecUsuario()[i].getListaSeguidores().getCantActualSeguidores() + seguidoresTotales;// El usuario i tiene x seguidores y se suma a seguidores totales
            }

            double seguidoresPromedio = ( seguidoresTotales/usuariosTotales );
            System.out.println("El promedio de seguidores de los usuarios es de:  "+seguidoresPromedio);
        }
    }

    /**
     * El sistema despliega el nombre del usuario con
     * menor cantidad de seguidores y la cantidad
     * que posee
     */
    @Override
    public void usuarioMenor() {

        String menosSeguidores = "";
        int menor = 99;

        for (int i = 0; i < listaUsuario.getCantActual(); i++) {
            if(menor > listaUsuario.getVecUsuario()[i].getListaSeguidores().getCantActualSeguidores()){

                menor = listaUsuario.getVecUsuario()[i].getListaSeguidores().getCantActualSeguidores();
                menosSeguidores = listaUsuario.getVecUsuario()[i].getNickname();
            }
        }

        System.out.println("El usuario con menor cantidad de seguidores es "+menosSeguidores+" con "+menor+" seguidores");

    }

    /**
     * El sistema despliega el nombre del usuario con
     * mayor cantidad de usuarios seguidos y la
     * cantidad que posee
     */
    @Override
    public void usuarioMayor() {

        String masSeguido = "";
        int mayor = -1;

        for (int i = 0; i < listaUsuario.getCantActual(); i++) {
            if(mayor<listaUsuario.getVecUsuario()[i].getListaSeguidos().getCantActualSeguidos()){

                mayor = listaUsuario.getVecUsuario()[i].getListaSeguidos().getCantActualSeguidos();
                masSeguido = listaUsuario.getVecUsuario()[i].getNickname();
            }
        }
        System.out.println("El usuario con mayor cantidad de seguidos es "+masSeguido+" con "+mayor+" seguidores");

    }

    /**
     * El sistema despliega la cantidad de Superposts
     * realizados por administradores en el
     * programa
     */
    @Override
    public void cantSuperPosts() {
        System.out.println("La cantidad de Superpost creados es de : "+cantidadDeSuperpostCreados);
    }

    /**
     * El sistema le permite al usuario logueado
     * registrar a otro usuario mediante el ingreso de
     * sus datos pero solo si el usuario loggeado es Administrador y que la contraseña sea mayor a 6 caracteres
     * y que tenga almenos una mayuscula y a la vez que el nickname ya no este registrado
     * @return
     */
    @Override
    public boolean registrarUsuario() {

        System.out.print("Nombre: ");
        String nombreUsuarioNuevo = scan.next();
        System.out.print("Apellido: ");
        String apellidoUsuarioNuevo = scan.next();
        System.out.print("Correo: ");
        String correoUsuarioNuevo = scan.next();


        System.out.print("Nickname: ");
        String nicknameUsuarioNuevo = scan.next();


        System.out.print("Password: ");
        String passwordUsuarioNuevo = scan.next();

        boolean mayuscula = false;
        for (int i = 0; i < passwordUsuarioNuevo.length(); i++) {
            if(Character.isUpperCase(passwordUsuarioNuevo.charAt(i))) {
                mayuscula = true;
            }
        }


        if(passwordUsuarioNuevo.length()>6 && mayuscula==true) {
            System.out.println("Tipo de cuenta" +
                    "\n -[1] Administrador" +
                    "\n -[2] Default");
            String tipoDeUsuarioNuevo = scan.next();

            switch (tipoDeUsuarioNuevo) {
                case "1": {
                    agregarUsuarioAdminLecturaInicial(nombreUsuarioNuevo, apellidoUsuarioNuevo, correoUsuarioNuevo, nicknameUsuarioNuevo, passwordUsuarioNuevo, true, 0, 0);
                    break;
                }
                case "2": {
                    agregarUsuarioNormalLecturaInicial(nombreUsuarioNuevo, apellidoUsuarioNuevo, correoUsuarioNuevo, nicknameUsuarioNuevo, passwordUsuarioNuevo, false, 0, 0);
                    break;
                }

                default: {
                    System.out.println("Dato ingresado incorrectamente");
                    break;
                }
            }
            listaUsuario.desplegarUsuarios();
        }else{
            System.out.println("La contraseña no es mañor a 6 caracteres");
        }
        return false;
    }

    /**
     * Esta funcion sobreescribira el archivo "usuarios.txt" con la informacion que se almaceno durante la ejecucion del sistema
     * @throws IOException
     */
    public void esctrituraUsuarios() throws IOException {

        ArchivoSalida archivoUsuario = new ArchivoSalida("usuarios.txt");

        for (int i = 0; i < listaUsuario.getCantActual(); i++) {
            Registro registroSalida = new Registro(8);
            registroSalida.agregarCampo(listaUsuario.getVecUsuario()[i].getNombre());
            registroSalida.agregarCampo(listaUsuario.getVecUsuario()[i].getApellido());
            registroSalida.agregarCampo(listaUsuario.getVecUsuario()[i].getEmail());
            registroSalida.agregarCampo(listaUsuario.getVecUsuario()[i].getNickname());
            registroSalida.agregarCampo(listaUsuario.getVecUsuario()[i].getPassword());
            if(listaUsuario.getVecUsuario()[i].getEsAdmin()==true){
                registroSalida.agregarCampo("Administrador");
            }else{
                registroSalida.agregarCampo("Default");
            }
            registroSalida.agregarCampo(listaUsuario.getVecUsuario()[i].getListaSeguidos().getCantActualSeguidos());//no esta actualizando datos de seguidores/seguidos (fixed?)
            registroSalida.agregarCampo(listaUsuario.getVecUsuario()[i].getListaSeguidores().getCantActualSeguidores());

            archivoUsuario.writeRegistro(registroSalida);
        }
        archivoUsuario.close();

    }

    /**
     * Esta funcion sobreescribira el archivo "usuarios.txt" con la informacion que se almaceno durante la ejecucion del sistema
     * @throws IOException
     */
    public void escrituraSeguidos() throws IOException {
        ArchivoSalida archivoUsuario = new ArchivoSalida("usuarios_seguidos.txt");

        for (int i = 0; i < listaUsuario.getCantActual(); i++) {

            Registro registroSalida = new Registro(2);
            if(listaUsuario.getVecUsuario()[i].getListaSeguidores().getCantActualSeguidores()!=0){//si el usuario tiene seguidores
                registroSalida.agregarCampo(listaUsuario.getVecUsuario()[i].getNickname());//nickame
                registroSalida.agregarCampo(listaUsuario.getVecUsuario()[i].getListaSeguidores().getCantActualSeguidores());//seguidores
                archivoUsuario.writeRegistro(registroSalida);

                for (int j = 0; j < listaUsuario.getVecUsuario()[i].getListaSeguidores().getCantActualSeguidores(); j++) {//suiguiente linea n vezes
                    Registro registroSalida2 = new Registro(2);
                    registroSalida2.agregarCampo(listaUsuario.getVecUsuario()[i].getListaSeguidores().getVecSeguidores()[j].getNombre());//
                    registroSalida2.agregarCampo(listaUsuario.getVecUsuario()[i].getListaSeguidores().getVecSeguidores()[j].getNickname());
                    archivoUsuario.writeRegistro(registroSalida2);
                }
            }
        }
        archivoUsuario.close();
    }

    /**
     * Esta funcion cierra el sistema llamando a las 2 clases de escritura de archivos
     * @throws IOException
     */
    @Override
    public void cerrarAplicacion() throws IOException {
        System.out.println("saliendo de la app");
        esctrituraUsuarios();
        System.out.println("usuarios.txt guardado correctamente");
        escrituraSeguidos();
        System.out.println("usuarios_seguidos.txt guardado correctamente");
    }
}
