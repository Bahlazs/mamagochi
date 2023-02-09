package com.codecool.grannymanager;


import com.codecool.grannymanager.model.Granny;
import com.codecool.grannymanager.model.enumgrannyproperties.Mood;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrannyManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrannyManagerApplication.class, args);
//		Granny granny1 = new Granny();
//		Granny granny2 = new Granny();
//		System.out.println(granny1.equals(granny2));
//		granny1.setMoodStat(Mood.GRUMPY);
//		System.out.println(granny1.equals(granny2));

	}

}
