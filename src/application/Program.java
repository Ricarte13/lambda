package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter full file path: ");
		String path = scanner.next();
		
		System.out.print("Enter salary: ");
		Double salary = scanner.nextDouble();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<Employee> list = new ArrayList<>();
			
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			Comparator<String> comp = (s1,s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> emails = list.stream()
					.filter(p -> p.getSalary() > salary)
					.map(p -> p.getEmail()).sorted(comp)
					.collect(Collectors.toList());
			
			System.out.println("Email of people whose salary is more than 2000.00");
			emails.forEach(System.out::println);
			
			
			
			
		}catch(IOException e) {
			System.out.println("Error" + e.getMessage());
		}
		
		scanner.close();

	}

}
