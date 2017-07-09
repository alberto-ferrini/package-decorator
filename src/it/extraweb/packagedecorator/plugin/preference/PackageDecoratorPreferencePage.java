package it.extraweb.packagedecorator.plugin.preference;

import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.nebula.widgets.tablecombo.TableCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import it.extraweb.packagedecorator.plugin.dto.Preference;
import it.extraweb.packagedecorator.plugin.dto.Preferences;
import it.extraweb.packagedecorator.plugin.util.MaterialColorPalette;
import it.extraweb.packagedecorator.plugin.util.PackageDecoratorUtils;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public class PackageDecoratorPreferencePage
extends PreferencePage
implements IWorkbenchPreferencePage, SelectionListener, ModifyListener {

	private Preferences preferences;
	private Text textPackage;

	private Table table;
	private Composite form;
	private TableCombo combo;
	private Button checkSub;
	private Button buttonAdd;
	private Button buttonRemove;
	private Button buttonMoveUp;
	private Button buttonMoveDown;

	@Override
	public void init(IWorkbench workbench) {
		preferences=PreferenceManager.loadPreferences();
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite panel=new Composite(parent, SWT.NULL);
		Label labelOverview=new Label(panel, SWT.LEFT);
		labelOverview.setText("Rules overview");
		FontData fontData = labelOverview.getFont().getFontData()[0];
		Font font = new Font(parent.getDisplay(), new FontData(fontData.getName(), fontData.getHeight(), SWT.BOLD));
		labelOverview.setFont(font);
		Label labelOverviewDescription=new Label(panel, SWT.LEFT);
		labelOverviewDescription.setText("The first rule that matches with the package will be applied.");
		Label labelOverviewDescription2=new Label(panel, SWT.LEFT);
		labelOverviewDescription2.setText("Select an element to edit.");

		Composite summary=new Composite(panel, SWT.NULL);
		table = new Table(summary, SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setLayout(new TableLayout());
		table.setHeaderVisible(true);
		table.addSelectionListener(this);
		String[] titles = {"Package name", "Color", "Sub"};
		int[] alignments={SWT.RIGHT,SWT.LEFT,SWT.CENTER};
		boolean[] resizables={true,false,false,false};

		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			TableColumn column = new TableColumn(table, alignments[loopIndex]);
			column.setText(titles[loopIndex]);
			column.setResizable(resizables[loopIndex]);
		}

		insertData();
		packTable();

		Composite buttons=new Composite(summary,SWT.NULL);
		buttonAdd=new  Button(buttons, SWT.NULL);
		buttonAdd.setText("Add");
		buttonAdd.addSelectionListener(this);
		buttonRemove=new  Button(buttons, SWT.NULL);
		buttonRemove.setText("Remove");
		buttonRemove.addSelectionListener(this);
		new Label(buttons, SWT.LEFT);
		buttonMoveUp=new  Button(buttons, SWT.NULL);
		buttonMoveUp.setText("Move up");
		buttonMoveUp.addSelectionListener(this);
		buttonMoveDown=new  Button(buttons, SWT.NULL);
		buttonMoveDown.setText("Move down");
		buttonMoveDown.addSelectionListener(this);
		GridLayoutFactory.swtDefaults().numColumns(1).generateLayout(buttons);

		GridLayoutFactory.swtDefaults().numColumns(2).generateLayout(summary);






		new Label(panel, SWT.HORIZONTAL | SWT.SEPARATOR);

		Label labelForm=new Label(panel, SWT.LEFT);
		labelForm.setFont(font);
		labelForm.setText("Edit details");

		form=new Composite(panel,SWT.NULL);
		Label labelPackage=new Label(form, SWT.NULL);
		labelPackage.setText("Package name:");
		labelPackage.setAlignment(SWT.RIGHT);

		textPackage=new Text(form, SWT.SINGLE|SWT.BORDER);
		textPackage.addModifyListener(this);
		textPackage.setText("");

		Label labelColor=new Label(form, SWT.NULL);
		labelColor.setText("Color:");
		labelColor.setAlignment(SWT.RIGHT);

		combo=new TableCombo(form, SWT.BORDER | SWT.READ_ONLY);
		combo.setLayoutData(new GridData(125, SWT.DEFAULT));

		// tell the TableCombo that I want 2 blank columns auto sized.
		combo.defineColumns(1);

		// set which column will be used for the selected item.
		combo.setDisplayColumnIndex(0);
		combo.addSelectionListener(this);

		for(String colorName:MaterialColorPalette.getInstance().getNames()){
			TableItem comboItem=new TableItem(combo.getTable(), SWT.NULL);
			comboItem.setImage(0, new Image(parent.getDisplay(), PackageDecoratorUtils.generateColorizedPackage(MaterialColorPalette.getInstance().getNameToHex().get(colorName))));
			comboItem.setText(0, colorName);
		}

		new Label(form,SWT.LEFT);

		checkSub=new Button(form, SWT.CHECK);
		checkSub.setText("Decorate subpackages");
		checkSub.setSelection(true);
		checkSub.addSelectionListener(this);

		new Label(panel,SWT.LEFT);

		GridLayoutFactory.swtDefaults().numColumns(2).generateLayout(form);

		GridLayoutFactory.swtDefaults().numColumns(1).generateLayout(panel);

		form.setVisible(false);
		return panel;
	}

	@Override
	public boolean performOk() {
		save();
		return super.performOk();
	}

	@Override
	protected void performApply() {
		save();
		for (int loopIndex = 0; loopIndex < table.getColumnCount(); loopIndex++) {
			table.getColumn(loopIndex).pack();
		}
		super.performApply();
	}

	private void save() {
		PreferenceManager.savePreferences(preferences);
	}

	@Override
	protected void performDefaults() {
		preferences=PreferenceManager.generateDefaultPreferences();
		table.removeAll();
		table.redraw();
		insertData();
		packTable();
		form.setVisible(false);
		// TODO Auto-generated method stub
		super.performDefaults();
	}
	
	private void packTable(){
		for (int loopIndex = 0; loopIndex < table.getColumnCount(); loopIndex++) {
			table.getColumn(loopIndex).pack();
		}
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if(e.getSource()==table){
			Preference preference=preferences.get(table.getSelectionIndex());
			textPackage.setText(preference.getPackageName());
			combo.select(MaterialColorPalette.getInstance().getHexes().indexOf(preference.getColor()));
			checkSub.setSelection(preference.getSubPackages());
			form.setVisible(true);
		}
		else if(e.getSource()==combo){
			Preference preference=preferences.get(table.getSelectionIndex());
			preference.setColor(MaterialColorPalette.getInstance().getHexes().get(combo.getSelectionIndex()));
			TableItem item=table.getSelection()[0];
			item.setImage(1,new Image(table.getDisplay(),PackageDecoratorUtils.generateColorizedPackage(preference.getColor())));
			item.setText(1,MaterialColorPalette.getInstance().getNames().get(combo.getSelectionIndex()));
			packTable();
		}
		else if(e.getSource()==checkSub){
			Preference preference=preferences.get(table.getSelectionIndex());
			preference.setSubPackages(checkSub.getSelection());
			TableItem item=table.getSelection()[0];
			if(preference.getSubPackages()){
				item.setImage(2,new Image(table.getDisplay(), new ImageData(this.getClass().getResourceAsStream("/icons/enabled.png"))));
			}
			else{
				item.setImage(2,new Image(table.getDisplay(), new ImageData(this.getClass().getResourceAsStream("/icons/disabled.png"))));
			}
		}
		else if(e.getSource()==buttonAdd){
			TableItem item=new TableItem(table, SWT.LEAD);
			item.setText(0,"(new)");
			item.setImage(1,new Image(table.getDisplay(),PackageDecoratorUtils.generateColorizedPackage(MaterialColorPalette.getInstance().getHexes().get(0))));
			item.setText(1,MaterialColorPalette.getInstance().getNames().get(0));
			item.setImage(2,new Image(table.getDisplay(), new ImageData(this.getClass().getResourceAsStream("/icons/enabled.png"))));
			item.setImage(3,new Image(table.getDisplay(), new ImageData(this.getClass().getResourceAsStream("/icons/disabled.png"))));
			preferences.add(new Preference("(new)", MaterialColorPalette.getInstance().getHexes().get(0), true));
		}
		else if(e.getSource()==buttonRemove){
			int index=table.getSelectionIndex();
			preferences.remove(index);
			table.remove(index);
			form.setVisible(false);
		}
		else if(e.getSource()==buttonMoveUp){
			int index=table.getSelectionIndex();
			if(index>0){
				Preference remove=preferences.remove(index);
				preferences.add(index-1,remove);
				table.removeAll();
				table.redraw();
				insertData();
				table.select(index-1);
			}
		}
		else if(e.getSource()==buttonMoveDown){
			int index=table.getSelectionIndex();
			if(index!=-1 && index<preferences.size()-1){
				Preference remove=preferences.remove(index);
				preferences.add(index+1,remove);
				table.removeAll();
				table.redraw();
				insertData();
				table.select(index+1);
			}
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyText(ModifyEvent e) {
		if(e.getSource()==textPackage){
			if(table.getSelectionIndex()!=-1){
				preferences.get(table.getSelectionIndex()).setPackageName(textPackage.getText());
				table.getSelection()[0].setText(0,textPackage.getText());
				packTable();
			}
		}
	}

	private void insertData() {
		for(Preference preference:preferences.getPreferences()){
			TableItem item = new TableItem(table, SWT.LEAD);
			item.setText(0,preference.getPackageName());
			item.setImage(1,new Image(table.getDisplay(),PackageDecoratorUtils.generateColorizedPackage(preference.getColor())));
			item.setText(1,MaterialColorPalette.getInstance().getHexToName().get(preference.getColor()));
			if(preference.getSubPackages()){
				item.setImage(2,new Image(table.getDisplay(), new ImageData(this.getClass().getResourceAsStream("/icons/enabled.png"))));
			}
			else{
				item.setImage(2,new Image(table.getDisplay(), new ImageData(this.getClass().getResourceAsStream("/icons/disabled.png"))));
			}
		}
	}
}