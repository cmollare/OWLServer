package OWLServer;


import java.util.Scanner;

/**
 * Created by christophe on 22/01/15.
 *
 * Class servant à rentrer des phrases dans la console.
 * Dans l'application finale, la console est censée
 * être remplacée par la sortie de la reco vocale.
 */
public class ConsoleInput {

    /**
     * Fonction de test de la class. Permet d'entrer des phrase jusqu'à ce qu'on rentre la lettre 'q'.
     */
    public static void main(String[] args) throws Exception {

        String cont="";

        while (!cont.equals("q"))
        {
            ConsoleInput console = new ConsoleInput();
            cont =  console.input();
            System.out.println(cont);
        }

    }

    /**
     * Fonction à appeler pour rentrer une phrase.
     *
     * @return retourne la phrase entrée par l'utilisateur.
     */
    public String input()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("moi :");
        return sc.nextLine();
    }
}
