import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.Scanner;

public class practica {
    public static void main (String[] args) throws IOException {
        //PREGUNTAR URL
        System.out.println("Ingrese el URL:");
        Scanner input = new Scanner(System.in);
        String url = input.nextLine();
        //CANTIDAD DE LINEAS DEL HTML       (A)
        System.out.println("lineas en el html: "+ Lineas(url) + ".");
        //PARRAFOS                          (B)
       System.out.println("Cantidad de párrafos en el html: " + Parrafos(url) +".");
       //IMAGENES EN PARRAFOS               (C)
        System.out.println("Imagenes en los tags de párrafos: "+ ImagenesP(url) + ".");
        //CANTIDAD DE FORMS (POST)          (D.1)
        System.out.println("Cantidad de (POST): " + CantidadFormsPost(url) + ".");
        //CANTIDAD DE FORMS (GET)           (D.2)
        System.out.println("Cantidad de (GET): " + CantidadFormsGet(url) + ".");
        //CANTIDAD DE INPUT Y TIPO          (E)
        System.out.println("Acápite E)");
        CantidadInput(url);



    }

public static int Lineas (String url) throws IOException{

        String html = Jsoup.connect(url).execute().body();
    int cantidad = 0;
    cantidad = html.split("\n").length;
    return cantidad;

}
 public static int Parrafos(String url) throws IOException {
        Document documento = Jsoup.connect(url).get();
        int cantidad = 0;
        cantidad = documento.select("p").size();

        return cantidad;
    }

    public static int ImagenesP(String url) throws IOException {
        Document documento = Jsoup.connect(url).get();
        int cantidad = 0;
        cantidad = documento.select("p img").size();

        return cantidad;
    }

    public static int CantidadFormsPost(String url) throws IOException{
        Document documento = Jsoup.connect(url).get();
        int cantidad = 0;
        cantidad = documento.select("form[method= 'post' ]").size();

        return cantidad;
    }

    public static int CantidadFormsGet(String url) throws IOException{
        Document documento = Jsoup.connect(url).get();
        int cantidad = 0;
        cantidad = documento.select("form[method= 'get' ]").size();

        return cantidad;
    }
    private static void CantidadInput(String url) throws IOException{
        Document document = Jsoup.connect(url).get();
        int cantForm =0 ,cantInput = 0;

// busco dentro de los elemenots form
        for(Element form : document.select("form")) {
            System.out.println("\nFormulario: " + cantForm);
            // recorro todos los elementos input del form y busco su tipo
            for(Element input : form.select("input")) {
                System.out.println("Input: " + cantInput + " su tipo es: " + input.attr("type"));
                cantInput++;
            }
            cantForm++;
        }
    }

}
