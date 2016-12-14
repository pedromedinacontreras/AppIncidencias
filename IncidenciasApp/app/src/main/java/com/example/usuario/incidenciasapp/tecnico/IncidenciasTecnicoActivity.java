package com.example.usuario.incidenciasapp.tecnico;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.usuario.incidenciasapp.R;
import com.example.usuario.incidenciasapp.adapters.IncidenciasPagerAdapter;
import com.example.usuario.incidenciasapp.adapters.IncidenciasTecnicoAdapter;

public class IncidenciasTecnicoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidencias_tecnico);
        toolbar = (Toolbar) findViewById(R.id.toolbar_tecnico_incidencias);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.pager_incidencias_tecnico);
        setSupportActionBar(toolbar);

        final IncidenciasTecnicoAdapter adapter = new IncidenciasTecnicoAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateAdapter(){
        final IncidenciasTecnicoAdapter adapter = new IncidenciasTecnicoAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
}
