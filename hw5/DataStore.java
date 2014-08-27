package mzhang8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class DataStore implements Serializable {
	ArrayList<HashMap<String, String>> resInfo = null;
	
	public DataStore(ArrayList<HashMap<String, String>> data) {
		resInfo = data;
	}
	
	public ArrayList<HashMap<String, String>> getResInfo() {
		return resInfo;
	}
	
	//Load data from file.
	public static DataStore load(String file) throws IOException, ClassNotFoundException {
		if((new File(file)).exists()) {
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream oin = new ObjectInputStream(fin);
			DataStore data = (DataStore)oin.readObject();
			oin.close();
			return data;
		}
		else
			return null;
	}
	
	//Save data to file.
	public static boolean save(ArrayList<HashMap<String, String>> res, String file) throws IOException {
		FileOutputStream fout = new FileOutputStream(file);
		ObjectOutputStream oout = new ObjectOutputStream(fout);
		oout.writeObject(new DataStore(res));
		oout.close();
		return true;
	}
}
