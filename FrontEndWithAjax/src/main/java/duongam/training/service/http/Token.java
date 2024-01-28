package duongam.training.service.http;

import duongam.training.dto.enums.ERole;

import java.util.ArrayList;
import java.util.List;

public class Token {
	public static final String HEADER = "Authorization";
	public static String API_KEY = "None";

	public static List<ERole> ROLE = new ArrayList<>();
}
