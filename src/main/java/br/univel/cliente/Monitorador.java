package br.univel.cliente;

import java.io.File;
import java.rmi.RemoteException;
import java.util.Date;

import br.univel.jshare.comum.Arquivo;

public class Monitorador implements Runnable {
	private Thread threadMonitor;
	private String diretorio;

	public static Monitorador monitorador;

	private Monitorador(String diretorio) {
		this.diretorio = diretorio;
		this.threadMonitor = new Thread(this);
		this.threadMonitor.start();
	}

	public static void publicarMinhaLista(final String dir) throws RemoteException {
		File diretorio = new File(dir);
		if (!diretorio.exists()) {
			diretorio.mkdir();
		}
		File arquivos[] = diretorio.listFiles();

		PanelCliente.getListaArquivos().clear();

		for (int i = 0; i < arquivos.length; i++) {
			File file = arquivos[i];
			Arquivo arquivo = new Arquivo();
			arquivo.setNome(file.getName().substring(0, file.getName().lastIndexOf(".")));
			arquivo.setExtensao(file.getName().substring((file.getName().lastIndexOf(".") + 1)));
			arquivo.setPath(file.getPath());
			arquivo.setDataHoraModificacao(new Date());
			arquivo.setTamanho(file.length());
			arquivo.setMd5(Md5Util.getMD5Checksum(file.getAbsolutePath()));
			arquivo.setId(i + 1);
			PanelCliente.getListaArquivos().add(arquivo);
		}

		if (PanelCliente.getListaArquivos() != null) {
			MainApp.getServidor().publicarListaArquivos(MainApp.getMeuCliente(), PanelCliente.getListaArquivos());
		}
	}

	@Override
	public void run() {
		Thread currentThread = Thread.currentThread();

		while (this.threadMonitor == currentThread) {
			try {
				publicarMinhaLista(diretorio);
				Thread.sleep(10000);
			} catch (RemoteException e) {
				PanelServidor.getTextArea().append("Erro ao publicar lista de arquivos!\n" + e.toString() + "\n");
			} catch (InterruptedException e) {
				PanelServidor.getTextArea().append("Erro ao publicar lista de arquivos!\n" + e.toString() + "\n");
			}
		}
	}

	/**
	 * @return the monitorador
	 */
	public synchronized static Monitorador getMonitorador(String diretorio) {
		if (monitorador == null) {
			monitorador = new Monitorador(diretorio);
		}
		return monitorador;
	}
}
