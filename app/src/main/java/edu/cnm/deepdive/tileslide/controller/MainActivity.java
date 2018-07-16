package edu.cnm.deepdive.tileslide.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import edu.cnm.deepdive.tileslide.R;
import edu.cnm.deepdive.tileslide.model.Frame;
import edu.cnm.deepdive.tileslide.view.FrameAdapter;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

  private static int PUZZLE_SIZE = 4;

  private Frame frame;
  private FrameAdapter adapter;
  private GridView tileGrid;
  private TextView movesCounter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    tileGrid = findViewById(R.id.tile_grid);
    tileGrid.setNumColumns(PUZZLE_SIZE);
    tileGrid.setOnItemClickListener(this);
    movesCounter = findViewById(R.id.move_counter);

    createPuzzle();


  }


  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
  int row = position / PUZZLE_SIZE;
  int col = position % PUZZLE_SIZE;
//  frame.move(row, col);
//  adapter.notifyDataSetChanged();
    if(frame.move(row, col)) {
      adapter.notifyDataSetChanged();
      countMoves();
//      if(frame.hasWin()) {
//        win();
//      }
    } else {
      Toast.makeText(this,"Cannot move tile!", Toast.LENGTH_SHORT).show();
    }

  }

  private void win() {
    Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show();
    tileGrid.setOnItemClickListener(null);

  }

  private void createPuzzle() {
    frame = new Frame(PUZZLE_SIZE, new Random());
    adapter = new FrameAdapter(this, frame);
    tileGrid.setAdapter(adapter);

  }

  private void countMoves() {
    movesCounter.setText(String.format(getString(R.string.move_counter), frame.getMoves()));

  }
}
