package Fabricas;

public abstract class FabricaSprites{
	
	 protected String rutaACarpetaSkin;
	 
	 public FabricaSprites(String ruta) {
		 this.rutaACarpetaSkin = ruta;
	 }
	 
	 // Getters Bloques.
	 public Sprite getBloquePregunta() {
		 return new Sprite(this.rutaACarpetaSkin + "/bloque_pregunta.gif");
	 }
	 
	 public Sprite getLadrilloSolido() {
		 return new Sprite(this.rutaACarpetaSkin + "/ladrillo_solido.png");
	 }
	 public Sprite getBloqueSolido() {
		 return new Sprite(this.rutaACarpetaSkin + "/bloque_solido.png");
	 }
	 
	 public Sprite getBloqueVacio() {
		 return new Sprite(this.rutaACarpetaSkin + "/bloque_vacio.png");
	 }
	 
	 // Getters Entidades
	 public Sprite getSuperChampinion() {
		 return new Sprite(this.rutaACarpetaSkin + "/super_champinion.gif");
	 }
	 
	 public Sprite getBolaDeFuego() {
		 return new Sprite(this.rutaACarpetaSkin + "/bola_de_fuego.gif");
	 }
	 
	 public Sprite getBuzzyBeetle() {
		 return new Sprite(this.rutaACarpetaSkin + "/buzzy_beetle.gif");
	 }
	 
	 public Sprite getChampinionVerde() {
		 return new Sprite(this.rutaACarpetaSkin + "/champinion_verde.gif");
	 }
	 
	 public Sprite getEstrella() {
		 return new Sprite(this.rutaACarpetaSkin + "/estrella.gif");
	 }

	 public Sprite getFlorDeFuego() {
		 return new Sprite(this.rutaACarpetaSkin + "/flor_de_fuego.gif");
	 }
	 
	 public Sprite getGoomba() {
		 return new Sprite(this.rutaACarpetaSkin + "/goomba.gif");
	 }
	 
	 public Sprite getKoopaTroopaCaparazon() {
		 return new Sprite(this.rutaACarpetaSkin + "/koopa_troopa_caparazon.gif");
	 }
	 
	 public Sprite getLakitu() {
		 return new Sprite(this.rutaACarpetaSkin + "/lakitu.gif");
	 }
	 
	 public Sprite getBajandoBandera(String rutaEstado) {
		 return new Sprite(this.rutaACarpetaSkin + rutaEstado);
	 }
	 
	 public Sprite getCorriendo(String rutaEstado) {
		 return new Sprite(this.rutaACarpetaSkin + rutaEstado);
	 }
	 
	 public Sprite getSaltando(String rutaEstado) {
		 return new Sprite(this.rutaACarpetaSkin + rutaEstado);
	 }
	 
	 public Sprite getQuieto(String rutaEstado) {
		 return new Sprite(this.rutaACarpetaSkin + rutaEstado);
	 }
	 
	 public Sprite getMoneda() {
		 return new Sprite(this.rutaACarpetaSkin + "/moneda.gif");
	 }
	 
	 public Sprite getPiranhaPlant() {
		 return new Sprite(this.rutaACarpetaSkin + "/piranha_plant.gif");
	 }
	 
	 public Sprite getSpinyBola() {
		 return new Sprite(this.rutaACarpetaSkin + "/spiny_bola.gif");
	 }
	 
	 public Sprite getSpiny() {
		 return new Sprite(this.rutaACarpetaSkin + "/spiny.gif");
	 }

	 public Sprite getKoopaTroopa() {
		 return new Sprite(this.rutaACarpetaSkin + "/koopa_troopa.gif");
	 }
	 
	 public Sprite getTuberiaMediana() {
		 return new Sprite(this.rutaACarpetaSkin + "/tuberia_media.png");
	 }
	 
	 public Sprite getTuberiaGrande() {
		 return new Sprite(this.rutaACarpetaSkin + "/tuberia_grande.png");
	 }

	public Sprite getVacio() {                       
		return new Sprite(this.rutaACarpetaSkin + "/vacio.png");
	}
}
