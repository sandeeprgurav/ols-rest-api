package in.ols.rest.model;

import java.util.ArrayList;
import java.util.List;

import in.ols.rest.model.TrainerDto;

public class TrainerView {

	private List<ColumnView> columnList = new ArrayList<>();
	private List<TrainerDto> rowList = new ArrayList<>();
	private TabView tabView = new TabView();

	public List<ColumnView> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<ColumnView> columnList) {
		this.columnList = columnList;
	}

	public List<TrainerDto> getRowList() {
		return rowList;
	}

	public void setRowList(List<TrainerDto> rowList) {
		this.rowList = rowList;
	}

	public TabView getTabView() {
		return tabView;
	}

	public void setTabView(TabView tabView) {
		this.tabView = tabView;
	}

	public static class TabView {
		List<TrainerDto> allRowList = new ArrayList<>();

		List<ColumnView> allColumnList = new ArrayList<>();

		public List<TrainerDto> getAllRowList() {
			return allRowList;
		}

		public void setAllRowList(List<TrainerDto> allRowList) {
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
