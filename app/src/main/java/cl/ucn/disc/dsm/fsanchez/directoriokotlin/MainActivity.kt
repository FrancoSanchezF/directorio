/*
 * Copyright (c) 2021 Franco Sanchez Flores.
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.fsanchez.directoriokotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Franco Sanchez-Flores
 */
class MainActivity : AppCompatActivity() {

    protected FuncionarioAdapter funcionarioAdapter;
    /**
     * @param savedInstanceState the state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the List (RecyclerView)
        final RecyclerView recyclerView = findViewById(R.id.am_rv_funcionarios);
        // The type of layout of RecyclerView
        recyclerView.setLayout.Manager(new LinearLayoutManager (this, RecyclerView.VERTICAL, false));

        // Build the Adapter
        this.funcionarioAdapter = new FuncionarioAdapter ();
        // Union of Adapter + RecyclerView
        recyclerView.setAdapter(this.funcionarioAdapter);

        // Build the ViewModel
        FuncionarioViewModel funcionarioViewModel = viewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())
            .create(FuncionarioViewModel.class);

        // Watch the List of Funcionario
        funcionarioViewModel.getFuncionarios().observe(this, funcionarios -> {
            // Set the funcionarios (from view model)
            funcionarioAdapter.setFuncionarios(funcionarios);
            // Refresh the Recycler (ListView)
            funcionarioAdapter.notifyDataSetChanged();
        });

        /*@Override
        protected void onStart() {
            super.onStart();
            // Run in the background
            AsyncTask.execute(() -> {

            List<Funcionario> theFuncionarios;

            // Read the funcionarios.json
            try (final InputStream is =
                super.getApplication().getAssets().open("funcionarios.json")) {

                // Get the Type oif List<Funcionarios> with reflection
                final Type funcionariosListType =
                    new TypeToken < List < Funcionario > > (){}.getType();

                // The reader
                final Reader reader = new InputStreamReader (is);

                // The json to object converter.
                final Gson gson = new GsonBuilder ().create();

                // Google Gson Black magic
                theFuncionarios = gson.fromJson(reader, funcionariosListType);

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            // Sort by name
            theFuncionarios.sort(Comparator(.comparing(Funcionario::GetNombre));

            // Populate the Adapter
            this.funcionarioAdapter.setFuncionarios(theFuncionarios);

            // Populate the Adapter
            this.funcionarioAdapter.setFuncionarios(theFuncionarios);

                // Notify / Update de GUI
            runOnUiThread(() -> {
                    this.funcionarioAdapater.notifyDataSetChanged());
                });


        });

        }*/
    }
}