package com.example.usuario.incidenciasapp.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.usuario.incidenciasapp.fragments.IncidenciasEnProcesoTecnicoFragment;
import com.example.usuario.incidenciasapp.fragments.IncidenciasTerminadasTecnicoFragment;

/**
 * Created by usuario on 13/12/16.
 */

public class IncidenciasTecnicoAdapter extends FragmentPagerAdapter {
    private Context context;
    public static int totalPages = 2;

    public IncidenciasTecnicoAdapter(Context context, FragmentManager fm){
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position){
        Fragment f = new Fragment();
        switch (position){
            case 0:
                f = new IncidenciasEnProcesoTecnicoFragment();
                break;
            case 1:
                f = new IncidenciasTerminadasTecnicoFragment();
                break;
        }
        return f;
    }

    @Override
    public int getCount(){
        return totalPages;
    }
}