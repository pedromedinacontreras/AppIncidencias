package com.example.usuario.incidenciasapp.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.usuario.incidenciasapp.Fragments.IncidenciasEnProcesoFragment;
import com.example.usuario.incidenciasapp.Fragments.IncidenciasPorAsignarFragment;
import com.example.usuario.incidenciasapp.Fragments.IncidenciasTerminadasFragment;

/**
 * Created by usuario on 6/12/16.
 */

public class IncidenciasPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    public static int totalPages = 3;

    public IncidenciasPagerAdapter(Context context, FragmentManager fm){
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position){
        Fragment f = new Fragment();
        switch (position){
            case 0:
                f = new IncidenciasPorAsignarFragment();
                break;
            case 1:
                f = new IncidenciasEnProcesoFragment();
                break;
            case 2:
                f = new IncidenciasTerminadasFragment();
                break;
        }
        return f;
    }

    @Override
    public int getCount(){
        return totalPages;
    }
}
