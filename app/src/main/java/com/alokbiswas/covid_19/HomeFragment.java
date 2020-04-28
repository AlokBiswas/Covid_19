package com.alokbiswas.covid_19;

import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {


   /* TextView mCases, mRecuperados, mMuertos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mCases = root.findViewById(R.id.tvCasos);
        mRecuperados = root.findViewById(R.id.tvRecuperados);
        mMuertos = root.findViewById(R.id.tvMuertos);
        obtenerdatos();
        return root;
    }

    private void obtenerdatos() {
       Call<Mundial> call = apiClient.getInstance().getApi().getInfoMundial();
       call.enqueue(new Callback<Mundial>() {
           @Override
           public void onResponse(Call<Mundial> call, Response<Mundial> response) {
               Mundial mundial = response.body();
               mCases.setText(mundial.getCases());
               mRecuperados.setText(mundial.getRecovered());
               mMuertos.setText(mundial.getDeaths());
           }

           @Override
           public void onFailure(Call<Mundial> call, Throwable t) {

           }
       });
    }*/
}
