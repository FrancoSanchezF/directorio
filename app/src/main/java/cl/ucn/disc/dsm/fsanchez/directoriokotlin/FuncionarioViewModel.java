/*
 * Copyright (c) 2021 Franco Sanchez Flores.
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 *                            The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 *                            THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.fsanchez.directoriokotlin;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import lombok.NonNull;

/**
 * The ViewModel of Funcionario.
 *
 * @author Franco Sanchez-Flores.
 */
public class FuncionarioViewModel extends AndroidViewModel {

  /**
   * The List of{@link Funcionario}.
   */
  private MutableLiveData<List<Funcionario>> funcionarios;

  /**
   * The Constructor.
   *
   * @param application to use.
   */
  public FuncionarioViewModel(@NonNull Application application) {
    super(application);
  }

  /**
   *
   * @return the LiveData of List of Funcionario.
   */
  public LiveData<List<Funcionario>> getFuncionarios() {

    // Lazy load
    if (this.funcionarios == null) {

      this.funcionarios = new MutableLiveData<>();
      this.loadFuncionarios();
    }

    return this.funcionarios;
  }

  /**
   * Read the Funcionarios from funcionarios.jsion.
   */
  public void loadFuncionarios() {

    // Run in the background
    AsyncTask.execute(() -> {

      List<Funcionario> theFuncionarios;

      // Read the funcionarios.json
      try (final InputStream is = super.getApplication().getAssets().open("funcionarios.json")) {

        // Get the Type oif List<Funcionarios> with reflection
        final Type funcionariosListType = new TypeToken<List<Funcionario>>(){}.getType();

        // The reader
        final Reader reader = new InputStreamReader(is);

        // The json to object converter.
        final Gson gson = new GsonBuilder().create();

        // Google Gson Black magic
        theFuncionarios = gson.fromJson(reader, funcionariosListType);

      } catch (IOException e) {
        e.printStackTrace();
        return;
      }

      this.funcionarios.postValue(theFuncionarios);

    });
  }
}
