package OWLServer.treeTaggerTools;

import java.util.Vector;

/**
 * Phrase constituent.
 * Chaque constituent peut contenir un autre constituent ou/et plusieurs instances de POSTag
 *
 * Created by christophe on 27/01/15.
 */
public class PhraseConst {

    protected Vector<PhraseConst> subPhraseConst;

    protected String tag;
    protected int numCONSTITUENT;
    protected int numPOSTAG;
    protected int depth;


    PhraseConst(String name){
        subPhraseConst = new Vector<PhraseConst>();
        this.tag = name;
        numCONSTITUENT=0;
        numPOSTAG=0;
        depth=0;
    }

    public void add(PhraseConst subConst) {
        subPhraseConst.add(subConst);
        subConst.depth+=this.depth+1; //increase depth of next nodes
        if (subConst.getClass().getName().toString() == "OWLServer.treeTaggerTools.PhraseConst") numCONSTITUENT++;
        else if (subConst.getClass().getName().toString() == "OWLServer.treeTaggerTools.POSTag") numPOSTAG++;
    }

    public void disp(){
        for(int i=0 ; i<this.depth ; i++) System.out.print("\t");
        System.out.println(tag);
        for (int i=0 ; i<subPhraseConst.size() ; i++) {
            subPhraseConst.get(i).disp();
        }
    }
}
