package com.bolsaideas.springboot.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {

	private String url;
	
	private Page<T> page;
	
	private int totalPag;
	
	private int numElem;
	
	private int pagActual;
	
	private List<PageItem> paginas;

	public PageRender(String url, Page<T> page) {
		super();
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<PageItem>();
		
		numElem = page.getSize();
		
		totalPag = page.getTotalPages();
		
		pagActual = page.getNumber() + 1;
		
		int desde, hasta;
		if(totalPag <= numElem) {
			desde=1;
			hasta = totalPag;
		} else {
			if(pagActual <= numElem/2) {
				desde = 1;
				hasta = numElem;
			} else if(pagActual >= totalPag - numElem/2) {
				desde = totalPag - numElem + 1;
				hasta = numElem;
			} else {
				desde = pagActual - numElem/2;
				hasta = numElem;
			}
		}
		
		
		for(int i = 0; i<hasta; i++) {
			paginas.add(new PageItem(desde + i, pagActual == desde +i));
		}
		
	}

	public String getUrl() {
		return url;
	}

	public Page<T> getPage() {
		return page;
	}

	public int getTotalPag() {
		return totalPag;
	}

	public int getPagActual() {
		return pagActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}
	
	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();	
	}
	
	public boolean isHasNext() {
		return page.hasNext();
	}
	
	public boolean isHasPrevious() {
		return page.hasPrevious();	
	}
	
	
}
