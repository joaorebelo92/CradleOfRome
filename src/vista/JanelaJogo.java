package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import modelo.Jogo;
import pt.ipleiria.estg.dei.gridpanel.GridPanel;
import java.awt.Color;
import java.awt.Label;
import javax.swing.JButton;
import javax.swing.JLabel;

public class JanelaJogo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	private Jogo jogo;
	private GridPanel GridPainelBonus;
	private GridPanel GridPainelPrincipal;
    private GridPanel GridPainelVida;
	private Thread threadTempo;
	private GridPanel GridPainelPontuacao;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaJogo frame = new JanelaJogo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JanelaJogo() {
		setResizable(false);
		setTitle("Cradle Of Rome @ DS & JR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel()
		{
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try{
					g.drawImage(ImageIO.read(JanelaJogo.class.getResource("/imagens/fundo/Fundo0.jpg")),0,0,this);
				}catch (IOException e){
					e.printStackTrace();
				}
			} 		
		};
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		GridPainelPrincipal = new GridPanel();
		GridPainelPrincipal.setRows(10);
		GridPainelPrincipal.setColumns(20);
		contentPane.add(GridPainelPrincipal, BorderLayout.CENTER);
		
		GridPainelVida = new GridPanel();
		GridPainelVida.setColumnSize(150);
		GridPainelVida.setRowSize(250);
		GridPainelVida.setColumns(1);
		GridPainelVida.setRows(2);
		contentPane.add(GridPainelVida, BorderLayout.EAST);
		
		GridPainelBonus = new GridPanel();
		GridPainelBonus.setShowGridLines(false);
		GridPainelBonus.setRowSize(100);
		GridPainelBonus.setRows(1);
		GridPainelBonus.setColumns(7);
		GridPainelBonus.setColumnSize(100);
		contentPane.add(GridPainelBonus, BorderLayout.SOUTH);
		
		//***********************************//
		jogo = new Jogo(GridPainelPrincipal, GridPainelBonus, GridPainelVida);
		
		GridPainelPontuacao = new GridPanel();
		GridPainelPontuacao.setRowSize(30);
		GridPainelPontuacao.setColumnSize(1300);
		GridPainelPontuacao.setBackgroundColor(new Color(255, 0, 0));
		GridPainelPontuacao.setBackground(new Color(204, 0, 0));
		GridPainelPontuacao.setShowGridLines(true);
		GridPainelPontuacao.setRows(1);
		GridPainelPontuacao.setColumns(1);
		contentPane.add(GridPainelPontuacao, BorderLayout.NORTH);
		
		final Runnable iterar = new Runnable(){
			public void run()
			{
				jogo.iterar();
				
				//tempo--;
			    GridPainelVida.repaint();
			    GridPainelBonus.repaint();
			    GridPainelPrincipal.repaint();
			    //System.out.println("iterar");
			};
		};
		
		threadTempo = new Thread()
		{
			@Override
			public void run() {
				
				super.run();
				while(true)
				{
					try{
						sleep(100);
						//jogo.iterar();
						SwingUtilities.invokeAndWait(iterar);
					}
					catch(InterruptedException | InvocationTargetException e)
					{
						e.printStackTrace();
					}
				}
			}
		};
		threadTempo.start();
		
	}

}
