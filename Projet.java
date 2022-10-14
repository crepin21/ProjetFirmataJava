/*
  Titre      : ProjetLibrairie
  Auteur     : Crepin Vardin Fouelefack
  Date       : 13/10/2022
  Description: Utilisation de la librairie firmata pour faire clignoter une led
  Version    : 0.0.1
*/

// Source: https://www.yorku.ca/professor/drsmith/2022/02/25/easy-java-arduino-with-firmata/

// Maven: com.github.kurbatov:firmata4j:2.3.8
//          (https://mvnrepository.com/artifact/com.github.kurbatov/firmata4j/2.3.8)
// Also we need to manually re-install SLF4J : org.slf4j:slf4j-jcl:1.7.3
//          (https://mvnrepository.com/artifact/org.slf4j/slf4j-jcl/1.7.3)

//Bibliotheques pour etablir la connexion USB
import org.firmata4j.firmata.*;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import java.io.IOException;

//Declaration de la classe du projet
public class Projet {

//Declaration de la methode main    
    public static void main(String[] args)


        throws IOException
        {
            //Declaration des variables
            String myPort = "COM3";                                                //Port USB utiliser sur le pc

            IODevice myGroveBoard = new FirmataDevice(myPort);                     //Declaration d'un objet associer au port du micro-controleur

            try 
            {   //Debut de la communication avec le difpositif
                myGroveBoard.start();                                              // start comms with board;
                System.out.println("Board started.");
                myGroveBoard.ensureInitializationIsDone();
            }

            catch (Exception ex) 
            {
                System.out.println("couldn't connect to board.");               //Si la communication echoue avec le dispositif

            }

            finally 
            {

                //Declaration des variables
                var myLED = myGroveBoard.getPin(4);

                //Configuration de la broche 4 en mode sortie
                myLED.setMode(Pin.Mode.OUTPUT);

                // LED D4 on.
                myLED.setValue(1);

                // Pause for half a second.
                try 
                {
                    Thread.sleep(500);
                }
    
                //Message en cas d'erreur de l'arret de la led
                catch(Exception ex)
                {
                    System.out.println("sleep error.");
                }

                // LED D4 off.
                myLED.setValue(0);

                // finish with the board.
                myGroveBoard.stop();  

                System.out.println("Board stopped.");

            }
        }   
}