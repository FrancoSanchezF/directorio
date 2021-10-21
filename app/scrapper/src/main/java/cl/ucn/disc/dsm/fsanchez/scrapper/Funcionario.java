package cl.ucn.disc.dsm.fsanchez.scrapper;

import lombok.Builder;
import lombok.Getter;

/**
 * The UCN Funcionario.
 *
 * @author Franco Sanchez-Flores
 */
@Builder
public final class Funcionario {

  /**
   * The ID.
   */
  @Getter
  private final Integer id;

  /**
   * The Nombre.
   */
  @Getter
  private final String nombre;

  /**
   * The Cargo.
   */
  @Getter
  private final String cargo;

  /**
   * The Unidad.
   */
  @Getter
  private final String unidad;

  /**
   * The Email.
   */
  @Getter
  private final String email;

  /**
   * The Telefono.
   */
  @Getter
  private final String telefono;

  /**
   * The Oficina.
   */
  @Getter
  private final String oficina;

  /**
   * The Direccion.
   */
  @Getter
  private final String direccion;


}
