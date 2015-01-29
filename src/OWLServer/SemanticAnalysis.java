package OWLServer;

import OWLServer.treeTaggerTools.Utterance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 * Created by christophe on 22/01/15.
 *
 * Class permettant de faire une analyse sémantique de la phrase à l'aide de TreeTagger.
 * (dans le dossier share).
 */
public class SemanticAnalysis {

    /**
     * Class utilisée pour représenter les données résultant de l'analyse par treeTagger.
     * (structure de données)
     */

    /*public class TreeTaggerResult {
        protected Vector<PhraseConst>

        /**
         * méthode pour afficher ce que contient l'instance de cette class
         * @return renvoie un type String permettant l'affichage
         */
        /*public String disp()
        {
            String res="--------------------------\n";
            res += "Token : " + this.token + "\nFunction :\n";
            for (String temp: this.function){
                res += "\t-" + temp + "\n";
            }
            res += "Lemma :\n";
            for (String temp: this.lemma){
                res += "\t-" + temp + "\n";
            }
            res += "Prob : " + this.prob + "\n";
            res += "--------------------------";

            return res;
        }
    }*/

    public class TreeTaggerChunk{

    }

    /**
     * fonction de test
     */
    public static void main(String[] args) throws IOException {

        ConsoleInput console = new ConsoleInput();
        SemanticAnalysis semAna = new SemanticAnalysis();

        semAna.analysis(console.input());

    }

    /**
     * Lance une analyse semantique utilisant TreeTagger.
     *
     * @param sentence Phrase à analyser
     *
     * @see OWLServer.SemanticAnalysis#MorphoSyntacticTagging
     */
    public void analysis(String sentence) throws IOException{

        Vector<Utterance> taggedSentence = this.MorphoSyntacticTagging(sentence);
    }

    protected void kindOfSentenceClassifier(Vector<Utterance> sentence){

    }

    /**
     *
     * @param sentence phrase à envoyer à treeTagger
     * @return un vecteur contenant chaque terme de la phrase et leur signification
     * @see OWLServer.treeTaggerTools.Utterance
     * @throws IOException
     */
    protected Vector<Utterance> MorphoSyntacticTagging(String sentence) throws IOException{

        //Lancement de treeTagger
        Runtime run = Runtime.getRuntime();

        String[] cmd = {
                "/bin/bash",
                "-c",
                "echo \"" + sentence + "\" | share/treeTagger/cmd/tagger-chunker-french"
        };
        Process process = run.exec(cmd);
        //Fin du processus

        //Lecture du résultat
        //TODO rajouter la lecture du flux stderr
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line="";
        //lecture des utterances et création des instances de classe Utterance
        Vector<Utterance> res = new Vector<Utterance>();
        while((line = reader.readLine()) != null) {
            if (line.matches("^<s>")){
                res.add(new Utterance(reader));
                res.lastElement().disp();
            }
            //res.add(this.resParsing(line));
        }

        return res;
    }
}
