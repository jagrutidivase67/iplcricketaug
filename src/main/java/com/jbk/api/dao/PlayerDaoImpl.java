package com.jbk.api.dao;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.api.entity.Player;






@Repository
public class PlayerDaoImpl implements PlayerDao {
	@Autowired
	private SessionFactory sessionfactory;
	
    
	@Override
	public boolean PlayerSave(Player player) {
		Session session = sessionfactory.openSession();
		  boolean  isAvailable=false;
	try {
		
		
			Player player1=session.get(Player.class, player.getplayerJersyNo());
			
			if(player1==null) {
				Transaction transaction= session.beginTransaction();
				session.save(player);
				transaction.commit();
				isAvailable=true;
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
				
			}
		}
		
	return isAvailable;
		
	}

	@Override
	public Player getPlayerByJersyNo(int playerJesyNo) {
		Session session = sessionfactory.openSession();
		Player player=null;
		try {
			player=session.get(Player.class, playerJesyNo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)
			{
				session.close();
			}
		}
		return player;
	}

	@Override
	public boolean injuredPlayer(int playerJesyNo) 
	{
		Session session = sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		Player player=null;
		boolean isInjured=false;
		try {
			player=session.get(Player.class,playerJesyNo );
			if(player!=null)
			{
				session.delete(player);
				transaction.commit();
				isInjured=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null) {
				session.close();
			}
		}
		return isInjured;
	}

	@Override
	public List<Player> getallPlayers() {
		Session session = sessionfactory.openSession();
		List<Player>list=null;
		try {
			{
			Criteria criteria=session.createCriteria(Player.class);
			list=criteria.list();
		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)
			{
				session.close();
			}
		}
		return list;
	}

	@Override
	public Boolean UpdateplayerTeam(Player player) {
    Session session=sessionfactory.openSession();
    Transaction transaction=session.beginTransaction();
    boolean playerisUpdated=false;
    try {
    	Player pl=session.get(Player.class, player.getplayerJersyNo());
      if(pl!=null)
      {
    	  session.evict(pl);
    	  session.update(player);
    	  transaction.commit();
    	  playerisUpdated=true;
      }
    }catch(Exception e) {
    	e.printStackTrace();
    }
	finally
	{
		if(session!=null)	
		{
			session.close();
		}
	}
	return playerisUpdated;
	}

	@Override
	public List<Player> getPlayerByName(String playerName) {
		Session session = sessionfactory.openSession();
		List<Player>list=null;
		try {
			String hql="  from Player where playerName = :xyz ";
			
			Query query = session.createQuery(hql);
			query.setParameter("xyz", playerName);
			list=query.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null)
			{
				session.close();
			}
		}
		return list;
	}

	@Override
	public List <Player> getmaxrunplayer() {
		Session session = sessionfactory.openSession();
		List <Player> maxrunplayer=null;
		int maxPlayerRuns=0;
		try {
         Criteria maxcriteria=session.createCriteria(Player.class);
			Criteria eqcriteria=session.createCriteria(Player.class);
	          maxcriteria.setProjection(Projections.max("playerRuns"));
			List<Integer> list=maxcriteria.list();
			maxPlayerRuns=list.get(0);
			eqcriteria.add(Restrictions.eq("playerRuns", maxPlayerRuns));
			maxrunplayer=eqcriteria.list();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maxrunplayer;
	}
	@Override
	public List<Player> getPlayerByTeamName(String playerTeam) {
		Session session = sessionfactory.openSession();
		List<Player>list=null;
		try {
			String hql="  from Player where playerTeam = :xyz ";
			
			Query query = session.createQuery(hql);
			query.setParameter("xyz", playerTeam);
			list=query.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null)
			{
				session.close();
			}
		}
		return list;
	}

	@Override
	public List<Player> getmaxwicketplayer() {
		Session session = sessionfactory.openSession();
		List <Player> maxwicketplayer=null;
		int maxPlayerWicket=0;
		try {
         Criteria maxcriteria=session.createCriteria(Player.class);
			Criteria eqcriteria=session.createCriteria(Player.class);
	          maxcriteria.setProjection(Projections.max("playerWicket"));
			List<Integer> list=maxcriteria.list();
			maxPlayerWicket=list.get(0);
			eqcriteria.add(Restrictions.eq("playerWicket", maxPlayerWicket));
			maxwicketplayer=eqcriteria.list();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return maxwicketplayer;
	}

	@Override
	public String deleteAllPlayers() {
		Session session=sessionfactory.openSession();
		List<Player>list=null;
		int count=0;
		String msg=null;
		try {
			Criteria criteria=session.createCriteria(Player.class);
			list=criteria.list();
			for(Player player:list)
			{
				int playerJersyNo=player.getplayerJersyNo();
				boolean deleted=injuredPlayer(playerJersyNo);
				if(deleted) {
					count++;
				}
				msg=count+"=players deleted";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	@Override
	public int readExcelToDB(List<Player> list) {
		int addedCount=0;
		for (Player player : list) {
			boolean isAdded=PlayerSave(player);
		
		if(isAdded) {
			addedCount=addedCount+1;
		
		}	
		}
		return addedCount;
		
	}

	}
