package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
	public static void main(String[] args) {

		// Сохранение

		String directory = "/Users/ludmilaengberg/hemjobb/Games/savegames/";

		GameProgress save1 = new GameProgress(10, 100, 1, 100.1);
		GameProgress save2 = new GameProgress(20, 200, 2, 200.2);
		GameProgress save3 = new GameProgress(30, 300, 3, 300.3);

		saveGame(directory + "сохранение 1", save1);
		saveGame(directory + "сохранение 2", save2);
		saveGame(directory + "сохранение 3", save3);

		ArrayList<String> arrayList = new ArrayList<>();

		for (int i = 0; i < arrayList.size(); i++) {
			String dp = directory + "сохранение" + (i+1);
			arrayList.add(dp);
		}

		arrayList.add(directory + "сохранение 1");
		arrayList.add(directory + "сохранение 2");
		arrayList.add(directory + "сохранение 3");

		zipFiles(directory + "zip.zip", arrayList);

		File newsave1 = new File(directory + "сохранение 1");
		File newsave2 = new File(directory + "сохранение 2");
		File newsave3 = new File(directory + "сохранение 3");

		if (newsave1.delete()) System.out.println("Файл \"сохранение 1\" удален");
		if (newsave2.delete()) System.out.println("Файл \"сохранение 2\" удален");
		if (newsave3.delete()) System.out.println("Файл \"сохранение 3\" удален");
	}

	private static void saveGame(String path, GameProgress gp) {
		try (FileOutputStream fos = new FileOutputStream(path);
			 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(gp);
			System.out.println(path + " сохранено");
		} catch (Exception ex) {
			System.out.println(path + " не сохранено");
		}
	}

	private static void zipFiles(String path, List<String> arrayList) {
		try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(path))) {
			for (String al : arrayList) {
				try (FileInputStream fis = new FileInputStream(al)) {
					ZipEntry entry = new ZipEntry(al);
					zos.putNextEntry(entry);
					while (fis.available() > 0) {
						zos.write(fis.read());
					}
					zos.closeEntry();
					System.out.println(al + " архивирован");
				} catch (Exception ex) {
					System.out.println(al + " ошибка архивации");
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}


