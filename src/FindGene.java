import edu.duke.*;
public class FindGene{
    /**
     * @description: Return the first correct stop codon's index, if there is no correct stop codon, return length of dna.
     * @param dna the dna String 
     * @param startIndex the index to start search
     * @param stopCodon the codon to stop, could be "TAA","TAG","TGA"
     * @return: the first occerence of correct stop codon
     */
    public static int findStopCodon(String dna,int startIndex,String stopCodon){
        int currIndex = startIndex;
        while(currIndex != -1){
            currIndex = dna.indexOf(stopCodon, currIndex+3);
            if(currIndex!=-1&&(currIndex-startIndex)%3 == 0){
                return currIndex;
            }
        }
        return dna.length();
    }

    public static String findGene(String dna){
        int indexOfStartCodon = dna.indexOf("ATG");
        if(indexOfStartCodon == -1) return "";
        int indexOfTAA = findStopCodon(dna, indexOfStartCodon, "TAA");
        int indexOfTAG = findStopCodon(dna, indexOfStartCodon, "TAG");
        int indexOfTGA = findStopCodon(dna, indexOfStartCodon, "TGA");
        int indexOfStopCodon = Math.min(Math.min(indexOfTAA, indexOfTAG), indexOfTGA);
        if(indexOfStopCodon == dna.length()) return "";
        return dna.substring(indexOfStartCodon, indexOfStopCodon+3);
    }
    public static StorageResource printAllGene(String dna){
        StorageResource sr = new StorageResource();
        String currGene;
        while(true){
            currGene = findGene(dna);
            if(currGene.isEmpty()){
                System.out.println("");
                break;
            }
            sr.add(currGene);
            int endIndex = dna.indexOf(currGene);
            if(endIndex!=-1)  dna = dna.substring(endIndex + currGene.length());
        }
        return sr;
    }
    public static void main(String[] args) {
        String dna = "";
        FileResource fr = new FileResource();
        dna = fr.asString().toUpperCase();
        StorageResource sr = new StorageResource();
        sr = printAllGene(dna);
        System.out.println(sr.size());

    }
}
