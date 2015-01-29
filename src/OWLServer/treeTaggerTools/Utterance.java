package OWLServer.treeTaggerTools;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by christophe on 28/01/15.
 */
public class Utterance {
    protected PhraseConst utterance;

    /**
     * Constructeur de la classe Utterance.
     * @param reader buffer du résultat de treeTagger
     */

    public Utterance(BufferedReader reader){
        //Nouvelle phrase (appelée s)
        utterance = new PhraseConst("s");

        try {
            this.create(reader, utterance);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * fonction récursive permettant de créer l'arbre résultant de treeTagger
     *
     * @param reader buffer sur la lecture du résultat
     * @param parent noeud parent de type PhraseConst
     * @throws IOException
     * @see OWLServer.treeTaggerTools.PhraseConst
     */

    protected void create(BufferedReader reader, PhraseConst parent) throws IOException {
        String line="";
        PhraseConst constituent = null;
        while ((line = reader.readLine()) != null){
            constituent = this.parsing(line);
            if (constituent == null) break;
            parent.add(constituent);

            //recursivité
            if (constituent.getClass().getName().toString() == "OWLServer.treeTaggerTools.PhraseConst"){
                this.create(reader, constituent);
            }
        }

    }

    /**
     * Cette fonction sert à parser une ligne de résultat treeTagger,
     * et la transformer en instance de classe correspondante.
     * @param lineToParse ligne de treeTagger qui va être parsée
     * @return instance de PhraseConst ou POSTag
     * @see OWLServer.treeTaggerTools.POSTag
     * @see OWLServer.treeTaggerTools.PhraseConst
     */

    protected PhraseConst parsing(String lineToParse){
        PhraseConst newConst=null;
        if (lineToParse.matches("^<[a-z:A-Z]+>")){
            String temp = lineToParse.substring(lineToParse.indexOf("<")+1, lineToParse.indexOf(">"));
            newConst = new PhraseConst(temp);
        }
        else if (lineToParse.matches("^</[a-z:A-Z]+>")){
        }
        else{
            String[] resString = lineToParse.split(" |\\t");
            newConst = new POSTag(resString[0], resString[1].split(":"), resString[2].split("\\|"));
        }

        return newConst;
    }

    public void disp(){
        utterance.disp();
    }

}
