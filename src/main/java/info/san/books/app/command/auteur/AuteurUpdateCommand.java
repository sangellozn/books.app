package info.san.books.app.command.auteur;

/**
 * MIT License
 *
 * Copyright (c) 2016 sangellozn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
/**
 * Command used for an auteur update (attributes).
 *
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public class AuteurUpdateCommand {

    private String id;

    private String nom;

    private String prenom;

    /**
     * Default constructor.
     *
     * @param id the auteur identifier.
     * @param nom the auteur name (updated value).
     * @param prenom the auteur first name (updated value).
     */
    public AuteurUpdateCommand(String id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    /**
     * Get the id property.
     *
     * @return the id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get the nom property.
     *
     * @return the nom.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Get the prenom property.
     *
     * @return the prenom.
     */
    public String getPrenom() {
        return this.prenom;
    }

}
