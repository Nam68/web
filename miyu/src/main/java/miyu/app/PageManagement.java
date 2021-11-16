package miyu.app;

public class PageManagement {
	
	public int startList(int cp, int size) {
		int start = (cp-1)*size +1;
		return start;
	}
	
	public int lastList(int cp, int count, int size) {
		int last = cp*size > count? count:cp*size;
		return last;
	}

	public int pageGroup(int cp, int size) {
		int group = cp%size==0? cp/size:cp/size+1;
		return group;
	}
	
	public int startPage(int cp, int size) {
		int group = pageGroup(cp, size);
		int start = startList(group, size);
		return start;
	}
	
	public int lastPage(int cp, int count, int size, int listSize) {
		int group = pageGroup(cp, size);
		int pageCount = pageGroup(count, listSize);
		int last = lastList(group, pageCount, size);
		return last;
	}
	
	public String paginationCode(int cp, int count, int pageSize, int listSize) {
		String previous = startPage(cp, pageSize)<=1? " disabled":"";
		String next = pageGroup(count, listSize)==lastPage(cp, count, pageSize, listSize)? " disabled":"";
		StringBuffer page = new StringBuffer();
		for(int i = startPage(cp, pageSize); i <= lastPage(cp, count, pageSize, listSize); i++) {
			page.append("<li class=\"page-item ");
			if(i == cp) {
				page.append("active");
			}
			page.append("\"><a class=\"page-link\" href=\"memoryList.do?cp="+i+"\">"+i+"</a></li>");
		}
		
		String code = "<nav aria-label=\"Page navigation example\">"
		+ "  <ul class=\"pagination\">"
		+ "    <li class=\"page-item"+previous+"\">"
		+ "      <a class=\"page-link\" href=\"memoryList.do?cp="+(startPage(cp, pageSize)-pageSize)+"\" aria-label=\"Previous\">"
		+ "        <span aria-hidden=\"true\">&laquo;</span>"
		+ "      </a>"
		+ "    </li>"
		
		+ page.toString()
		
		+ "    <li class=\"page-item"+next+"\">"
		+ "      <a class=\"page-link\" href=\"memoryList.do?cp="+(lastPage(cp, count, pageSize, listSize)+1)+"\" aria-label=\"Next\">"
		+ "        <span aria-hidden=\"true\">&raquo;</span>"
		+ "      </a>"
		+ "    </li>"
		+ "  </ul>"
		+ "</nav>";
		
		return code;
	}
	
}
