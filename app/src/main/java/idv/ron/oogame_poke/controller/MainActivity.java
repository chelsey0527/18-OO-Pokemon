package idv.ron.oogame_poke.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import java.util.ArrayList;
import java.util.List;
import idv.ron.oogame_poke.R;
import idv.ron.oogame_poke.model.Pokemon;

// 首頁
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handleViews();
    }

    private void handleViews() {
        // 取得野生男人後將圖片顯示在ImageView上
        List<Pokemon> fieldPokemons = Pokemon.getFieldPokemons();
        List<ImageView> imageViews = new ArrayList<>();
        ImageView imageView1 = findViewById(R.id.imageView1);
        ImageView imageView2 = findViewById(R.id.imageView2);
        ImageView imageView3 = findViewById(R.id.imageView3);
        ImageView imageView4 = findViewById(R.id.imageView4);
        imageViews.add(imageView1);
        imageViews.add(imageView2);
        imageViews.add(imageView3);
        imageViews.add(imageView4);
        for (int i = 0; i < imageViews.size(); i++) {
            final Pokemon fieldPokemon = fieldPokemons.get(i);
            imageViews.get(i).setImageResource(fieldPokemon.getImage());
            // 點擊野生男人後跳出選單讓user選擇捕捉方式
            imageViews.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(fieldPokemon, view);
                }
            });
        }
    }

    private void showPopupMenu(final Pokemon fieldPokemon, View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.hunt_menu);
        // 如果沒有男人，移除「使用我的男人」選項，只能用愛來獵捕男人
        if (Pokemon.getMyPokemons().size() == 0) {
            popupMenu.getMenu().removeItem(R.id.myPokemonHunt);
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent();
                switch (item.getItemId()) {
                    // 選擇「使用我的男人」會開啟百寶箱頁面供user挑選作戰用的男人
                    case R.id.myPokemonHunt:
                        intent.setClass(MainActivity.this, ChasemanActivity.class);
                        break;
                    // 預設是使用愛捕捉，會開啟用愛捕捉頁面
                    default:
                        intent.setClass(MainActivity.this, heartHuntActivity.class);
                        break;
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("fieldPokemon", fieldPokemon);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            }
        });
        popupMenu.show();
    }


    // 點擊主角會開啟男人
    public void onMeClick(View view) {
        Intent intent = new Intent(this, ChasemanActivity.class);
        startActivity(intent);
    }



}
