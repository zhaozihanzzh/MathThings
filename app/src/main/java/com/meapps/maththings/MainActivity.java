package com.meapps.maththings;

import android.app.*;
import android.os.*;
import com.meapps.*;
import android.widget.*;
import java.util.*;
import android.view.*;

public class MainActivity extends Activity {

	private String inputString = "";
	private ArrayAdapter methodsAdapter;
	private EditText mainEditor;
	private TextView resultView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		App.addActivity(MainActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		mainEditor = (EditText) findViewById(R.id.main_text_editor);
		inputString = mainEditor.getEditableText().toString();
		List<String> methods = new ArrayList<>();
		String[] methodName= getResources().getStringArray(R.array.arstr_method);
		for (int i = 0; i < methodName.length; i++) {
			methods.add(methodName[i]);
		}
		methodsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, methods);
		methodsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		final Spinner methodSpinner = (Spinner) findViewById(R.id.main_spinner);
		methodSpinner.setAdapter(methodsAdapter);
		resultView = (TextView) findViewById(R.id.main_result_view);
		Button calculate = (Button) findViewById(R.id.main_calc_btn);
		calculate.setOnClickListener(new Button.OnClickListener(){
				@Override
				public void onClick(View p1) {
					String input = mainEditor.getEditableText().toString();
					if (!input.substring(input.length()).equals(",")) {
						input = input + ",";
					}
					List<String> nums = new ArrayList<>();
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < input.length(); i++) {
						String current = input.substring(i, i + 1);
						if (current.equals(",")) {
							nums.add(sb.toString());
							sb.delete(0, sb.length());
						} else {
							sb.append(current);
						}
					}

					switch (methodSpinner.getSelectedItemPosition()) {
						case 0:
							resultView.setText(Calculator.calcAverage(nums));
					}

				}
			});
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		App.removeActivity(MainActivity.this);
	}

}
