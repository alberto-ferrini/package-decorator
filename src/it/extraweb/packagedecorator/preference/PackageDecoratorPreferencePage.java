package it.extraweb.packagedecorator.preference;

import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.nebula.widgets.tablecombo.TableCombo;
import org.eclipse.swt.SWT;
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

import it.extraweb.packagedecorator.util.MaterialColorPalette;
import it.extraweb.packagedecorator.util.PackageDecoratorUtils;

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
implements IWorkbenchPreferencePage {

	@Override
	public void init(IWorkbench workbench) {


	}

	@Override
	protected Control createContents(Composite parent) {
		Composite panel=new Composite(parent, SWT.NULL);
		Composite summary=new Composite(panel, SWT.NULL);
		Table table = new Table(summary, SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setHeaderVisible(true);
		String[] titles = { "Package name","Color", "SUB", "EMPTY"};

		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			TableColumn column = new TableColumn(table, SWT.NULL);
			column.setText(titles[loopIndex]);
		}

		TableItem item = new TableItem(table, SWT.NULL);
		item.setText("business");
		item.setText(0,"business");
		item.setText(1,"Blue");
		item.setText(2, "yes");
		item.setText(3, "yes");

		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			table.getColumn(loopIndex).pack();
		}

		Composite buttons=new Composite(summary,SWT.NULL);
		Button buttonAdd=new  Button(buttons, SWT.NULL);
		buttonAdd.setText("Add");
		Button buttonRemove=new  Button(buttons, SWT.NULL);
		buttonRemove.setText("Remove");
		Label separator=new Label(buttons, SWT.NULL);
		separator.setText(" ");
		Button buttonMoveUp=new  Button(buttons, SWT.NULL);
		buttonMoveUp.setText("Move up");
		Button buttonMoveDown=new  Button(buttons, SWT.NULL);
		buttonMoveDown.setText("Move down");
		GridLayoutFactory.swtDefaults().numColumns(1).generateLayout(buttons);
		
		Label label=new Label(summary, SWT.NULL);
		label.setText("Prova");
		GridLayoutFactory.swtDefaults().numColumns(2).generateLayout(summary);
		
		Composite form=new Composite(panel,SWT.NULL);
		Label labelPackage=new Label(form, SWT.NULL);
		labelPackage.setText("Package name:");
		labelPackage.setAlignment(SWT.RIGHT);
		
		Text textPackage=new Text(form, SWT.SINGLE|SWT.BORDER);
		textPackage.setText("");
		
		Label labelColor=new Label(form, SWT.NULL);
		labelColor.setText("Color:");
		labelColor.setAlignment(SWT.RIGHT);
		
		TableCombo combo=new TableCombo(form, SWT.BORDER | SWT.READ_ONLY);
		combo.setLayoutData(new GridData(125, SWT.DEFAULT));

		// tell the TableCombo that I want 2 blank columns auto sized.
		combo.defineColumns(1);
		
		// set which column will be used for the selected item.
		combo.setDisplayColumnIndex(0);
		
		MaterialColorPalette palette=new MaterialColorPalette();
		for(String color:palette.getMap().keySet()){
			TableItem comboItem=new TableItem(combo.getTable(), SWT.NULL);
			ImageData imageData=PackageDecoratorUtils.colorize(PackageDecoratorUtils.getPackageIcon(),palette.getMap().get(color));
			comboItem.setImage(0, new Image(parent.getDisplay(), imageData));
			String name=color.replace('_', ' ');
			name=name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase();
			comboItem.setText(0, name);
		}
		
		Label labelSubfolder=new Label(form,SWT.NULL);
		labelSubfolder.setText(" ");
		
		Button checkSubfolder=new Button(form, SWT.CHECK);
		checkSubfolder.setText("Decorate subpackages");
		checkSubfolder.setSelection(true);
		
		Label labelEmptyPackages=new Label(form,SWT.NULL);
		labelEmptyPackages.setText(" ");
		
		Button checkEmptyPackages=new Button(form, SWT.CHECK);
		checkEmptyPackages.setText("Decorate void packages");
		checkEmptyPackages.setSelection(true);
		
		GridLayoutFactory.swtDefaults().numColumns(2).generateLayout(form);
		
		GridLayoutFactory.swtDefaults().numColumns(1).generateLayout(panel);
		
		return panel;
	}
}