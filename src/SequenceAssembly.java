import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SequenceAssembly
{
	public static final String READS_FILE_NAME = "BorreliaFull_ExactReads.fna";
	//Filenames: BorreliaFull_ExactReads.fna BorreliaReduced_ExactReads.fna generatedReads.fna 
	public static final String OUTPUT_FILE = "generatedContigs.txt";
	
	public static void main(String args[]) 
	{
		long startTime = System.nanoTime();
		int kmerSize = 25;
		
		constructGraph(new File(READS_FILE_NAME), kmerSize);
		
		long contigGenerationStartTime = System.nanoTime();
		generateContigs();
		long contigGenerationEndTime = System.nanoTime();
		System.out.println("Time to generate contigs(ms): " + (contigGenerationEndTime - contigGenerationStartTime) / 1000000);
		
		long endTime = System.nanoTime();
		System.out.println("Program execution time(ms): " + (endTime - startTime) / 1000000);
	}
	
	public static void constructGraph(File readsFile, int kmerSize) 
	{
		try (Scanner fileIn = new Scanner(readsFile)) 
		{
			String currentLine = "";
			StringBuilder read = new StringBuilder();
			int readCount = 0;
			
			new OverlapGraph();
			//OverlapGraph.getInstance().setKmerSize(kmerSize);
			//System.out.println("kmer size: " + OverlapGraph.getInstance().getKmerSize());
			
			while (fileIn.hasNextLine())
			{
				currentLine = fileIn.nextLine();

				if (currentLine.startsWith(">")) {
					if (!read.toString().equals("")) {
						//addKmersToGraph(read.toString().toUpperCase());
						readCount++;
					}
					read = new StringBuilder();
				} 
				else
					read.append(currentLine.trim());
			}

			if (!read.toString().equals("")) {
				//addKmersToGraph(read.toString().toUpperCase());
				readCount++;
			}

			System.out.println("Number of reads processed: " + readCount);
		}
		catch (FileNotFoundException e) {
			System.err.println("File not found: " + READS_FILE_NAME);
		}
	}
	
	public static void generateContigs()
	{
		
	}
}
