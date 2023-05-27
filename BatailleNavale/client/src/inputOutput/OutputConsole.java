package inputOutput;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * La classe OutputConsole implémente l'interface iOutput et définit les
 * méthodes pour afficher les messages à l'utilisateur via la console.
 */
public class OutputConsole implements iOutput {

  private ResourceBundle messages;
  private final String root = "messages";

  public OutputConsole() {
    Locale locale = new Locale.Builder().setLanguage("fr").setRegion("FR").build();
    messages = ResourceBundle.getBundle(root, locale); 
  }

  @Override
  public boolean verifClef(String clef){
    return messages.containsKey(clef);
  }

  @Override
  public void afficherMsg(String clef, Object[] params) {
    String message;
    StringBuilder sb = new StringBuilder();
    String str = null;

    switch (clef) {
      case "ATK_HIST" : 
        
        if (params == null) {
          message = messages.getString("ATK_HIST_VIDE");
        } else {
          str = messages.getString("ATK_HIST_CONT");
          sb.append(messages.getString(clef));
      
          for (Object param : params) {
            Object[] atkParams = ((String) param).split(":");
            sb.append(MessageFormat.format(str, atkParams));
          }
      
          sb.append("\n");
          message = sb.toString();
        }
        break;
      case "PLAT" :
        sb.append(messages.getString(clef));
        str = messages.getString("PLAT_CONT");
      
        for (Object param : params) {
          Object[] atkParams = ((String) param).split(":");
          sb.append(MessageFormat.format(str, atkParams));
        }
        sb.append("\n");
        message = sb.toString();
        break;
      default :
        if(params != null && params.length != 0){
          message = messages.getString(clef);
          message = MessageFormat.format(message, params);
        } else {
          message = messages.getString(clef);
        }
    }
    System.out.print(message);
  }

}
