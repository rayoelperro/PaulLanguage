package paul.language.source;

public enum paul_values {
	print_d,
	return_d,
	null_v,
	
	normalize_var("normal"),
	file_var("file"),
	input_var("input");
	
	private String filetipe = "none";
	
	paul_values(String filetipe){
		this.filetipe = filetipe;
	}
	
	paul_values(){
		
	}
	
	public String archivo(){
		return filetipe;
	}
}