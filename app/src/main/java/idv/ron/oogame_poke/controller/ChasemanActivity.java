package idv.ron.oogame_poke.controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import idv.ron.oogame_poke.R;
import idv.ron.oogame_poke.model.Pokemon;

public class ChasemanActivity extends AppCompatActivity {
    private Pokemon fieldPokemon;
    private boolean setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man);
        setTitle(R.string.textMyPokemons);        // 如果是因為捕捉野生男人才來到此頁，fieldPokemon不為null

        if (getIntent().getExtras() != null) {
            fieldPokemon = (Pokemon) getIntent().getExtras().getSerializable("fieldPokemon");
            setListener = true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        RecyclerView rvPokemon = findViewById(R.id.rvPokemon);
        if (Pokemon.getMyPokemons().size() == 0) {
            Toast.makeText(this, R.string.textNoPokemon, Toast.LENGTH_SHORT).show();
            return;
        }
        rvPokemon.setLayoutManager(new GridLayoutManager(
                this, 3));
        rvPokemon.setAdapter(new PokemonAdapter(this, Pokemon.getMyPokemons()));
    }

    private class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {
        Context context;
        List<Pokemon> myPokemons;

        PokemonAdapter(Context context, List<Pokemon> myPokemons) {
            this.context = context;
            this.myPokemons = myPokemons;
        }

        @Override
        public int getItemCount() {
            return myPokemons.size();
        }

        class PokemonViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView tvName, tvHp;

            PokemonViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
                tvName = itemView.findViewById(R.id.tvName);
                tvHp = itemView.findViewById(R.id.tvPopulation);
            }
        }

        @NonNull
        @Override
        public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.item_view_man, parent, false);
            return new PokemonViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
            final Pokemon myPokemon = myPokemons.get(position);
            holder.imageView.setImageResource(myPokemon.getImage());
            holder.tvName.setText(myPokemon.getName());
            String hpInfo = myPokemon.getPopulation() + "/" + myPokemon.getFullPopulation();
            holder.tvHp.setText(hpInfo);

            // true代表因為捕捉野男人才來到此頁，user可點選自己的男人與野生的對戰
            if (setListener) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ChasemanActivity.this,
                                ManHuntActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("fieldPokemon", fieldPokemon);
                        bundle.putSerializable("myPokemon", myPokemon);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        // 對戰完畢會回到此頁，不可再點擊男人對戰，因此取消listener
                        setListener = false;
                    }
                });
            }
        }

    }
}
