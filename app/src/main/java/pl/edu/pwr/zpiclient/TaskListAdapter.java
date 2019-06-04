package pl.edu.pwr.zpiclient;

/**
 * Created by bartek on 24.04.2019.
 */


import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;



public class TaskListAdapter extends ArrayAdapter {

    private ArrayList tasks;
    private WorkerTaskListActivity mainActivity;
    private TextView taskNameText, startDateText;
    private ImageButton statusButton;
    private ConstraintLayout cl;
    private boolean active;

    public TaskListAdapter(Context context, ArrayList<Task> tasks,boolean act) {

        super(context, 0, tasks);

        this.tasks=tasks;
        this.mainActivity=(WorkerTaskListActivity) context;
        this.active=act;
    }


    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int pos) {
        return tasks.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Task task = (Task)getItem(position);


        /*if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.song_layout, parent, false);
        }*/
        LayoutInflater inflater = (LayoutInflater) mainActivity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.task_list_layout, null);
        }






        taskNameText = convertView.findViewById(R.id.taskNameText);
        startDateText = convertView.findViewById(R.id.startDateText);
        statusButton = convertView.findViewById(R.id.taskStatusButton);

        cl=convertView.findViewById(R.id.taskListLayoutBackground);






        taskNameText.setText(task.name);
        startDateText.setText(task.data);
        /*if(active) {
            switch (task.getStatus()) {
                case (1):
                    statusButton.setBackgroundColor(Color.GREEN);
                    break;
                case (2):
                    statusButton.setBackgroundColor(Color.RED);
                    break;
                default:
                    //stanowiskoText.setText("Nieznane stanowisko");
                    break;
            }
        }else{
            statusButton.setVisibility(View.INVISIBLE);
        }*/





        return convertView;
    }


}


