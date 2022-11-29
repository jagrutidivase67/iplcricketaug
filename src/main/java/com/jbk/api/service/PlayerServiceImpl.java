package com.jbk.api.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.api.dao.PlayerDao;
import com.jbk.api.entity.Player;

@Service
public class PlayerServiceImpl implements PlayerService {
	@Autowired
	PlayerDao playerdao;
	int  totalplayersInSheet=0;
	@Override
	public boolean PlayerSave(Player player) {
		boolean isAvailable=playerdao.PlayerSave(player);
		
		return isAvailable;
	}

	@Override
	public Player getPlayerByJersyNo(int playerJersyNo) {
		
		return playerdao.getPlayerByJersyNo(playerJersyNo);
	}

	@Override
	public boolean injuredPlayer(int playerJersyNo) {
		
		return playerdao.injuredPlayer(playerJersyNo);
	}

	@Override
	public List<Player> getallPlayers() {
		
		return playerdao.getallPlayers();
	}

	@Override
	public Boolean UpdateplayerTeam(Player player) {
		
		return playerdao.UpdateplayerTeam(player);
	}

	@Override
	public   List<Player> getPlayerByName(String playerName) 
	{
		
		return playerdao.getPlayerByName(playerName);
	}

	@Override
	public List<Player> getmaxrunplayer() {
		
		return playerdao.getmaxrunplayer();
	}

	@Override
	public List<Player> getPlayerByTeamName(String playerTeam) {
		return playerdao.getPlayerByTeamName(playerTeam);
	}

	@Override
	public List<Player> getmaxwicketplayer() {
		
		return playerdao.getmaxwicketplayer();
	}

	@Override
	public String deleteAllPlayers() 
	{
		return playerdao.deleteAllPlayers();
	}
	
	public List<Player> readExcel(String path) {
		Player player = null;
	
		List<Player> list = new ArrayList<>();
	try {
			FileInputStream fis = new FileInputStream(new File(path));
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
		    totalplayersInSheet = sheet.getLastRowNum();
			Iterator<Row> rows = sheet.rowIterator();
			int count = 0;

			while (rows.hasNext()) {
				Row row = rows.next();
				player = new Player();
				if (count == 0) {
					count++;
					continue;
				}

				Iterator<Cell> cells = row.cellIterator();

				while (cells.hasNext()) {
					Cell cell = cells.next();
					int col = cell.getColumnIndex();
					switch (col) {
					case 0: {
						player.setplayerJersyNo((int) cell.getNumericCellValue());
						break;
					}
					case 1: {
						player.setplayerName(cell.getStringCellValue());
						break;
					}
					case 2: {
						player.setplayerCountry(cell.getStringCellValue());

						break;
					}
					case 3: {
						player.setplayerTeam(cell.getStringCellValue());
					
						break;
					}
				case 4:
						{
							player.setplayerAge((int) cell.getNumericCellValue());

							break;
						}
				case 5:
				{
					player.setplayerRole(cell.getStringCellValue());
                   break;
					
				}
				case 6:
				{
					player.setplayerRuns((int) cell.getNumericCellValue());

				}
				case 7:
				{
					player.setplayerWicket((int) cell.getNumericCellValue());

				}
				
				default:
					break;
				}
					}
				
				list.add(player);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}



	@Override
	public String uploadsheet(MultipartFile File, HttpSession session) {
		int addedCount = 0;
		String path = session.getServletContext().getRealPath("/uploaded");
		String filename = File.getOriginalFilename();

		try {
			byte[] data = File.getBytes();
			FileOutputStream fileoutputstream = new FileOutputStream(
					new File(path + java.io.File.separator + filename));
			fileoutputstream.write(data);
			List<Player> list = readExcel(path + java.io.File.separator + filename);
			for (Player player : list) {
				System.out.println(player);
			}
			addedCount = playerdao.readExcelToDB(list);
		}catch(Exception e) {
			e.printStackTrace();
		}
	return "total player in sheet="+totalplayersInSheet+" , added player count="+addedCount;
}

}
	
	