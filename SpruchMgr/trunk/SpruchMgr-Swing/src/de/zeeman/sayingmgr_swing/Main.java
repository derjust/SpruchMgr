/**
 * Copyright (c) 2007-2009 RÖPERWEISE Systems GmbH, Gehrden - GERMANY. All rights reserved.
 */
package de.zeeman.sayingmgr_swing;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.zeeman.sayingmgr_ejb.ejb.SayingDAOBean;
import de.zeeman.sayingmgr_ejb.ejb.SayingDAORemote;
import de.zeeman.sayingmgr_ejb.entity.Picture;
import de.zeeman.sayingmgr_ejb.entity.Saying;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		/*
		 * get a initial context. By default the settings in the file
		 * jndi.properties are used. You can explicitly set up properties
		 * instead of using the file.
		 */
		// Properties properties = new Properties();
		// properties.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");
		// properties.put("java.naming.factory.url.pkgs","=org.jboss.naming:org.jnp.interfaces");
		// properties.put("java.naming.provider.url","localhost:1099");
		// 
		Context context;
		try {
			context = new InitialContext();
			Object o = context.lookup("SpruchMgr/SayingDAOBean/remote");
			SayingDAORemote beanRemote = (SayingDAORemote) o;

			Saying s = beanRemote.getSaying(1);
			Picture data = s.getAuthor().getPicture();
			System.out.println(data.getLength());
			System.out.println(data.getMime());
			

			ByteArrayInputStream bais = new ByteArrayInputStream(data.getImage());
			final BufferedImage image = ImageIO.read(bais);
			
			JFrame fmMain = new JFrame() {
				@Override
				public void paint(Graphics g2d) {
					g2d.drawImage(image, 0, 25, null);
				}
			};
			fmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			fmMain.setSize(image.getWidth(), image.getHeight()+25);
			fmMain.setResizable(false);
			fmMain.setVisible(true);
			

		} catch (NamingException e) {
			e.printStackTrace();
			/*
			 * I rethrow it as runtimeexception as there is really no need to
			 * continue if an exception happens and I do not want to catch it
			 * everywhere.
			 */
			throw new RuntimeException(e);
		}
	}

}
