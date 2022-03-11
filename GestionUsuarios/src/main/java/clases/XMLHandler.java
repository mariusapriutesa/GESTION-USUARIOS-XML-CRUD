



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 *
 * @author Rafa
 */
public class XMLHandler {
    /*-- Variable para almacenar el XML. El documento se carga una vez ejecutado
    el método loadXML --*/
    private static Document document;
    /*-- Método para cargar el archivo XML en la variable document
    Es el primer método a ejecutar para realizar cualquer operación
    con un documento XML --*/
    public static void loadXML(){
        try {
            File archivo = new File("src/main/java/resources/UsuariosXML.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            document = documentBuilder.parse(archivo);
        }catch (Exception e){
            e.printStackTrace();
        }// Fin de try-catch
    } // Fin método loadXML
    
    /*-- Método para guardar el archivo XML si ha sido modificado --*/
    public static void saveXML(){
        try{
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "4");
            Result output = new StreamResult(new File("src/main/java/resources/UsuariosXML.xml"));
            Source input = new DOMSource(document);
            transformer.transform(input, output);
        }catch (Exception e){
            e.printStackTrace();
        } //Fin de try-catch
    } // Fin metodo saveXML
    public static void crearUsuario(Usuario usuario){
    loadXML();
    Element root= document.getDocumentElement();
    Element eUsuario=document.createElement("usuario");
   
    Attr attr=document.createAttribute("dni");
    attr.setValue(usuario.getDni());
    eUsuario.setAttributeNode(attr);
    
    Attr attr1=document.createAttribute("nombre");
    attr1.setValue(usuario.getNombre());
    eUsuario.setAttributeNode(attr1);
    
    Attr attr2=document.createAttribute("direccion");
    attr2.setValue(usuario.getDireccion());
    eUsuario.setAttributeNode(attr2);
    
    Attr attr3=document.createAttribute("telefono");
    attr3.setValue(usuario.getTelefono());
    eUsuario.setAttributeNode(attr3);
    
    Attr attr4=document.createAttribute("correo");
    attr4.setValue(usuario.getCorreo());
    eUsuario.setAttributeNode(attr4);
    
    Attr attr5=document.createAttribute("fecha");
    attr5.setValue(usuario.getFecha());
    eUsuario.setAttributeNode(attr5);
    
    root.appendChild(eUsuario);
    saveXML();
    
    }// Fin metodo craerUsuario
    public static ArrayList<Usuario>obtenerLista(){
    ArrayList<Usuario> listaUsuarios=new ArrayList<Usuario>()  ;
       loadXML();
    
    NodeList nodeListUsuarios= document.getElementsByTagName("usuario");
        for (int i = 0; i < nodeListUsuarios.getLength(); i++) {
            Node nodeUsuario =nodeListUsuarios.item(i);
            
            if (nodeUsuario.getNodeType()==Node.ELEMENT_NODE) {
                Element eUsuario=(Element) nodeUsuario;
                Usuario usuario= new Usuario(eUsuario.getAttribute("dni"),
                eUsuario.getAttribute("nombre"),
                        eUsuario.getAttribute("telefono"),
                        eUsuario.getAttribute("direccion"),
                        eUsuario.getAttribute("correo"),
                        eUsuario.getAttribute("fecha")   );
                 listaUsuarios.add(usuario);
               
            }//fin if
            
        }//fin bucle for
    return listaUsuarios;
    
    }//fin metodo obtenerListaUsuario
    
    public static Usuario buscarUsuario(String dni){
    Usuario u=new Usuario();
    loadXML();
     //******************************************************************
    NodeList nodeListUsuarios= document.getElementsByTagName("usuario");
        for (int i = 0; i < nodeListUsuarios.getLength(); i++) {
            Node nodeUsuario =nodeListUsuarios.item(i);
            
            if (nodeUsuario.getNodeType()==Node.ELEMENT_NODE) {
                Element eUsuario=(Element) nodeUsuario;
                
                if (eUsuario.getAttribute("dni").equals(dni)) {
                u= new Usuario(eUsuario.getAttribute("dni"),
                eUsuario.getAttribute("nombre"),
                        eUsuario.getAttribute("telefono"),
                        eUsuario.getAttribute("direccion"),
                        eUsuario.getAttribute("correo"),
                        eUsuario.getAttribute("fecha")   );
                } 
                
            }//fin if
            
        }//fin bucle for
        //***********************************************************************
    return u;
    }//fin metodo buscar usuario
    public static boolean modificarUsuario(Usuario usuario){
    boolean usuarioEncontrado=false;
    loadXML();
    
     NodeList nodeListUsuarios= document.getElementsByTagName("usuario");
        for (int i = 0; i < nodeListUsuarios.getLength(); i++) {
            Node nodeUsuario =nodeListUsuarios.item(i);
            if (nodeUsuario.getNodeType()==Node.ELEMENT_NODE) {
                Element eUsuario=(Element) nodeUsuario;
                
                if (eUsuario.getAttribute("dni").equals(usuario.getDni())) {
                usuarioEncontrado=true;
                
                eUsuario.setAttribute("nombre",usuario.getNombre());
                 eUsuario.setAttribute("direccion",usuario.getNombre());
                  eUsuario.setAttribute("telefono",usuario.getTelefono());
                   eUsuario.setAttribute("correo",usuario.getCorreo());
                    eUsuario.setAttribute("fecha",usuario.getFecha());
                    break;
                     } 
                
            }//fin if
            
        }//fin bucle for
        
    saveXML();
    return usuarioEncontrado;
    }
    
    
    public static boolean borrarUsuario(String dni){
    
    
    
       boolean usuarioEncontrado=false;
    loadXML();
     Element root= document.getDocumentElement();
     NodeList nodeListUsuarios= document.getElementsByTagName("usuario");
        for (int i = 0; i < nodeListUsuarios.getLength(); i++) {
            Node nodeUsuario =nodeListUsuarios.item(i);
            if (nodeUsuario.getNodeType()==Node.ELEMENT_NODE) {
                Element eUsuario=(Element) nodeUsuario;
                
                if (eUsuario.getAttribute("dni").equals(dni)) {
                usuarioEncontrado=true;
                root.removeChild(nodeUsuario);
               
                    break;
                     } 
                
            }//fin if
            
        }//fin bucle for
        
    saveXML();
    return usuarioEncontrado;
    
    
    
    }
    
}//fin clase XMLHandler
