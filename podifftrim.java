import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class podifftrim {
	public static void main(String[] args) {
		if (args.length > 0) {
			System.err.println("No args! Use pipes!");
			System.exit(1);
		} else {
			doIt();
		}
	}
	
	private static BufferedReader in;
	
	private static void doIt() {
		in = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		StringBuffer block = new StringBuffer();
		boolean currentBlockIsInteresting = false;
		while(true) {
			line = readLine();
			if (line == null || line.trim().equals("") ||
					(line.length() >= 2 && line.substring(0, 2).equals("@@"))
					) { // EOF, or new "block" found
				//find out if buffer contains something interesting, write to System.out if so
				if (currentBlockIsInteresting) {
					System.out.println("~~~~~~~~~~~~ Interesting block begins here ~~~~~~~~~~~~\n" + block);
				} else {
					System.out.println("¤¤¤¤¤¤¤¤¤¤¤¤ Uninteresting block begins here ¤¤¤¤¤¤¤¤¤¤¤¤\n" + block);
				}
				
				if (line == null)
					System.exit(0);
				else {
					block = new StringBuffer();
					currentBlockIsInteresting = false;
				}
			}
			
			//is current line interesting? if so, whole block is interesting:
			if (line.length() >= 2 &&
					(line.charAt(0) == '+' || line.charAt(0) == '-') && // only changed lines are interesting
					(
					line.charAt(1) != '#' || // but changed comments aren't interesting
					line.contains("fuzzy") // except, changed fuzzy comments ARE interesting
					)) {
				currentBlockIsInteresting = true;
			}
			block.append(line + "\n"); // add the line to buffer in either case
		}
	}
	
	private static String readLine() {
		try {
			return in.readLine();
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
