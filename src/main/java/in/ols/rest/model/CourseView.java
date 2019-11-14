package in.ols.rest.model;

import java.util.ArrayList;
import java.util.List;

public class CourseView {

	private List<ColumnView> columnList = new ArrayList<>();
	private List<CourseDto> rowList = new ArrayList<>();
	private TabView tabView = new TabView();

	public List<ColumnView> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<ColumnView> columnList) {
		this.columnList = columnList;
	}

	public List<CourseDto> getRowList() {
		return rowList;
	}

	public void setRowList(List<CourseDto> rowList) {
		this.rowList = rowList;
	}

	public TabView getTabView() {
		return tabView;
	}

	public void setTabView(TabView tabView) {
		this.tabView = tabView;
	}

	public static class TabView {
		List<CourseDto> allRowList = new ArrayList<>();

		List<ColumnView> allColumnList = new ArrayList<>();

		public List<CourseDto> getAllRowList() {
			return allRowList;
		}

		public void setAllRowList(List<CourseDto> allRowList) {
			this.allRowList = allRowList;
		}

		public List<ColumnView> getAllColumnList() {
			return allColumnList;
		}

		public void setAllColumnList(List<ColumnView> allColumnList) {
			this.allColumnList = allColumnList;
		}

	}

}
