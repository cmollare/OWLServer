package OWLServer.treeTaggerTools;

/**
 * POSTag contient les annotations part of speech
 * (fonction du mot dans la phrase)
 *
 * Created by christophe on 27/01/15.
 */
public class POSTag extends PhraseConst {

    protected String token; //mot original
    protected String[] POS; //Part-Of-Speech Tag
    protected String[] lemma; //Lemme => forme cannonique du mot (infinitif...)

    /**
     * Constructeur
     * @param token mot original
     * @param posTag Part-Of-Speech Tag
     * @param lemma Forme cannonique du mot
     */
    public POSTag(String token, String[] posTag, String[] lemma){
        super(posTag[0]);
        this.token = token;
        this.POS = posTag;
        this.lemma = lemma;
    }

    /**
     * Fonction d'affichage de la classe
     */

    public void disp(){
        String res="";
        String prefix="";
        for (int i=0 ; i<depth ; i++) prefix+="\t";

        res += prefix + "Token : " + this.token + "\n" + prefix + "POS Tag : ";
        for (String temp: this.POS){
            res += prefix+"-" + temp + " ";
        }
        res += "\n" + prefix + "Lemma : ";
        for (String temp: this.lemma){
            res += "-" + temp + " ";
        }

        System.out.println(res);
    }
}
