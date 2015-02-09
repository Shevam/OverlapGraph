import java.io.File;

public class Main {
	public static final String READS_FILE_NAME = "BorreliaFull_ExactReads.fna";
	//Filenames: BorreliaFull_ExactReads.fna BorreliaReduced_ExactReads.fna generatedReads.fna 
	public static final String OUTPUT_FILE = "generatedContigs.txt";
	public static final int MINIMUM_OVERLAP_LENGTH = 10;
	
	public static void main(String args[]) 
	{
		long readGenerationStartTime = System.nanoTime();
		//StaticMethods.generateReads(SEQUENCE_FILE, READ_SIZE, GENERATED_READS_FILE);
		long readGenerationEndTime = System.nanoTime();
		System.out.println("Time to generate reads(ms): " + (readGenerationEndTime - readGenerationStartTime) / 1000000);
		
		long graphConstructionStartTime = System.nanoTime();
		StaticMethods.constructGraph(new File(READS_FILE_NAME), MINIMUM_OVERLAP_LENGTH);
		long graphConstructionEndTime = System.nanoTime();
		System.out.println("Time to construct graph(ms): " + (graphConstructionEndTime - graphConstructionStartTime) / 1000000);
		
		long contigGenerationStartTime = System.nanoTime();
		StaticMethods.generateContigs();
		long contigGenerationEndTime = System.nanoTime();
		System.out.println("Time to generate contigs(ms): " + (contigGenerationEndTime - contigGenerationStartTime) / 1000000);
		
		System.out.println("Total execution time(ms): " + (contigGenerationEndTime - readGenerationStartTime) / 1000000);
	}
}
