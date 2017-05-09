package co.edu.uniandes.estampala.services;

import java.lang.reflect.Type;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import spoon.Launcher;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import co.edu.uniandes.estampala.dto.Module;
import co.edu.uniandes.estampala.dto.pom.Model;
import co.edu.uniandes.estampala.dto.pom.Plugin.Configuration;

public class DiverterService {
	
	private String featureide_path = null;
	private String estampala_source_code_path = null;
	
	private Map<String, String> features = null;

	public DiverterService() {
		super();

		Properties properties = new Properties();        
        InputStream fi = getClass().getResourceAsStream("/co/edu/uniandes/estampala/resources/config.properties");
        try {
            properties.load(fi);
        } catch (IOException ex) {
            Logger.getLogger(DiverterService.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        this.featureide_path = properties.getProperty("featureide_path");
        this.estampala_source_code_path = properties.getProperty("estampala_source_code_path");
        
        try {
            fi.close();
        } catch (IOException ex) {
            Logger.getLogger(DiverterService.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	// CONFIGURAR PRODUCTO
	public void execute() {
		
		this.readFeatureConfig();
		this.generateConfigFiles();
		this.configModules();
		this.configAspectsWithMaven();
		
		
		try {
			// COMPILAR BACKEND APP
			String cmd = "mvn -f " + estampala_source_code_path + System.getProperty("file.separator") + "estampala.server.core" + " clean install";
			RunCommand.run(cmd);
			String msg = "Se realiza compilación de Backend APP (clean & install).";
	        Logger.getLogger(DiverterService.class.getName()).log(Level.INFO, msg);
	        
	        // 	CONSTRUIR BACKEND APP
	        cmd = "mvn -f " + estampala_source_code_path + System.getProperty("file.separator") + "estampala.server.core" + " package";
			RunCommand.run(cmd);
			msg = "Se construye Backend APP (package).";
	        Logger.getLogger(DiverterService.class.getName()).log(Level.INFO, msg);
	        
	        // CONSTRUIR FRONTEND APP
//	        String cmd = "ember build --input-path=" + estampala_source_code_path + System.getProperty("file.separator") + "web" + " --environment=production";
//			System.out.println(cmd);
//	        RunCommand.run(cmd);
//			String msg = "Se construye Frontend APP (ember build).";
//	        Logger.getLogger(DiverterService.class.getName()).log(Level.INFO, msg);	        
		} catch (IOException e) {
			Logger.getLogger(SecurityProcessor.class.getName()).log(Level.SEVERE, null, e);
		}		
	}
	
	// LEER CONFIGURACION DEL PRODUCTO
	private void readFeatureConfig() {		
		String path = this.featureide_path + System.getProperty("file.separator") + "configs" + System.getProperty("file.separator") + "default.config";
		this.features = this.readTextFile(path);
		String msg = "Archivo de configuración de featureIDE cargado: " + path;
        Logger.getLogger(DiverterService.class.getName()).log(Level.INFO, msg);
	}
	
	// GENERAR ARCHIVO DE CONFIGURACION DE CLIENTE WEB (EMBERJS)
	private void generateConfigFiles() {		
        List<Module> modules = new LinkedList<Module>();
		modules.add(new Module("catalog"));
		modules.add(new Module("payment"));
		modules.add(new Module("report"));
		modules.add(new Module("users"));
		
		String path_prop = estampala_source_code_path + System.getProperty("file.separator") + "estampala.server.core" + System.getProperty("file.separator");
		path_prop += "commons" + System.getProperty("file.separator") +  "src" + System.getProperty("file.separator") + "main" + System.getProperty("file.separator");
		path_prop += "resources" + System.getProperty("file.separator") + "features.properties";
        
		// Properties File for Backend App
		Map<String, Boolean> features = new HashMap<String, Boolean>();
		features.put("security", true);
		
		for (Module module : modules) {
			
			// Catalog Module 
			if(module.getName().equals("catalog")) {
				// Json File Config for Frontend App
				module.addFeature("rating", this.features.containsKey("Rating"));
				module.addFeature("calificar", this.features.containsKey("Calificar"));
				module.addFeature("info_detallada", this.features.containsKey("Info Detallada"));
				module.addFeature("compartir_estampa", this.features.containsKey("Compartir Estampa Redes Sociales"));
				module.addFeature("compartir_camiseta", this.features.containsKey("Compartir Camiseta Redes Sociales"));				
				
				features.put("rating", this.features.containsKey("Rating"));
				features.put("calificar", this.features.containsKey("Calificar"));
				features.put("info_detallada", this.features.containsKey("Info Detallada"));
				features.put("compartir_estampa", this.features.containsKey("Compartir Estampa Redes Sociales"));
				features.put("compartir_camiseta", this.features.containsKey("Compartir Camiseta Redes Sociales"));
			}	
			
			// Payment Module
			if(module.getName().equals("payment")) {
				// Json File Config for Frontend App
				module.addFeature("adquirir_tarjeta_regalo", this.features.containsKey("Adquirir Tarjeta Regalo"));
				module.addFeature("tarjeta_credito", this.features.containsKey("Tarjeta Credito"));
				module.addFeature("tarjeta_regalo", this.features.containsKey("Tarjeta Regalo"));
				
				features.put("adquirir_tarjeta_regalo", this.features.containsKey("Adquirir Tarjeta Regalo"));
				features.put("tarjeta_credito", this.features.containsKey("Tarjeta Credito"));
				features.put("tarjeta_regalo", this.features.containsKey("Tarjeta Regalo"));
			}
			
			// Report Module
			if(module.getName().equals("report")) {
				// Json File Config for Frontend App
				module.addFeature("ventas_usuario", this.features.containsKey("Consultar Ventas por Usuario"));
				module.addFeature("estado_ventas", this.features.containsKey("Consultar Estado Ventas"));
				module.addFeature("estampas_rating", this.features.containsKey("Consultar Estampas Rating"));
				
				features.put("ventas_usuario", this.features.containsKey("Consultar Ventas por Usuario"));
				features.put("estado_ventas", this.features.containsKey("Consultar Estado Ventas"));
				features.put("estampas_rating", this.features.containsKey("Consultar Estampas Rating"));
			}
			
			// Users Module
			if(module.getName().equals("users")) {
				// Json File Config for Frontend App
				module.addFeature("auth_facebook", this.features.containsKey("Facebook"));
				module.addFeature("auth_twitter", this.features.containsKey("Twitter"));
				
				features.put("auth_facebook", this.features.containsKey("Facebook"));
				features.put("auth_twitter", this.features.containsKey("Twitter"));
			}
			
			this.generatePropertiesFile(path_prop, features);
		}
		
		// Generar archivo JSON
		Gson gson = new Gson();
        Type type = new TypeToken<List<Module>>() {}.getType();
        String json = gson.toJson(modules, type);
        
        String path = this.estampala_source_code_path + System.getProperty("file.separator") + "web";
        path += System.getProperty("file.separator") + "app" + System.getProperty("file.separator") + "features.json";
        this.saveTextFile(json, path);
        
        String msg = "Archivo de configuración para Frontend APP creado: " + path;
        Logger.getLogger(DiverterService.class.getName()).log(Level.INFO, msg);
	}
	
	// CONFIGURAR MODULOS
	private void configModules() {
		String path_pom = estampala_source_code_path + System.getProperty("file.separator") + "estampala.server.core";
		path_pom += System.getProperty("file.separator") +  "pom.xml";
		
		// Leer POM: Estampala        
		Model pom = this.readPOM(path_pom);
		
		String msg = "Archivo POM general cargado: " + path_pom;
        Logger.getLogger(DiverterService.class.getName()).log(Level.INFO, msg);
        
        // Modificar POM
		List<String> list = pom.getModules().getModule();
		int index = -1;
		for (int i=0; i<list.size(); i++) {
			if( list.get(i).equals("report") ) {
				index = i;
			}
		}
		
		if( index != -1 && !this.features.containsKey("Consultar Ventas por Usuario") && !this.features.containsKey("Consultar Estado Ventas") && !this.features.containsKey("Consultar Estampas Rating")) {
			list.remove(index);
		} 
		else {
			if(index == -1) {
				pom.getModules().getModule().add("report");
			}
		}
		
		// Guardar POM: Estampala
		this.writePOM(pom, path_pom);
		msg = "Archivo POM general actualizado: " + path_pom;
        Logger.getLogger(DiverterService.class.getName()).log(Level.INFO, msg);
	}
	
	// CONFIGURAR ASPECTOS
	private void configAspectsWithMaven() {
		String path_pom = estampala_source_code_path + System.getProperty("file.separator") + "estampala.server.core" + System.getProperty("file.separator");
		path_pom += "%s" + System.getProperty("file.separator") +  "pom.xml";
		
		// Leer POM: Gift Card - Payment Module
        path_pom = String.format(path_pom, "payment");
		Model pom = this.readPOM(path_pom);
		
		String msg = "Archivo POM del Módulo Payment cargado: " + path_pom;
        Logger.getLogger(DiverterService.class.getName()).log(Level.INFO, msg);
		
		// Modificar POM
        Configuration conf = pom.getBuild().getPlugins().getPlugin().get(0).getConfiguration();
        
		if(this.features.containsKey("Adquirir Tarjeta Regalo") || this.features.containsKey("Tarjeta Regalo")) {
			
			if(conf.getAny().isEmpty()) {
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				try {
					DocumentBuilder db = dbf.newDocumentBuilder();
					Document document = db.newDocument();
					
					Element excludes = document.createElement("excludes");
					Element exclude = document.createElement("exclude");
					
					Text ecl = document.createTextNode("payment/aspects/giftcard/BuyGiftCard.java");
					exclude.appendChild(ecl);
					excludes.appendChild(exclude);
					
					conf.getAny().add(excludes);				
				} catch (ParserConfigurationException e) {
					Logger.getLogger(SecurityProcessor.class.getName()).log(Level.SEVERE, null, e);
				}	      
			}
//          <excludes>
//	            <!--<exclude>payment/aspects/giftcard/BuyGiftCard.java</exclude>-->
//	        </excludes>
		}
		else {
			if(!conf.getAny().isEmpty()) {
				conf.getAny().remove(0);
			}			
		}
		
		// Guardar POM: Gift Card - Payment Module
		this.writePOM(pom, path_pom);
		msg = "Archivo POM del Módulo Payment actualizado: " + path_pom;
        Logger.getLogger(DiverterService.class.getName()).log(Level.INFO, msg);
	}
	
	private void generatePropertiesFile(String path, Map<String, Boolean> features) {
		Properties prop = new Properties();
		OutputStream output = null;

		try {
			output = new FileOutputStream(path);

			// set the properties value
			features.forEach((k,v) -> prop.setProperty(k, String.valueOf(v)) );

			// save properties to project root folder
			prop.store(output, null);
			
			String msg = "Archivo de configuración para Backend APP creado: " + path;
	        Logger.getLogger(DiverterService.class.getName()).log(Level.INFO, msg);

		} catch (IOException io) {
			Logger.getLogger(SecurityProcessor.class.getName()).log(Level.SEVERE, null, io);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					Logger.getLogger(SecurityProcessor.class.getName()).log(Level.SEVERE, null, e);
				}
			}

		}
	}
	
	private Model readPOM(String path) {
		try {
			File file = new File(path);
			JAXBContext context = JAXBContext.newInstance(Model.class);
	
			Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
			Model pom = (Model)((JAXBElement<?>)jaxbUnmarshaller.unmarshal(file)).getValue();
			return pom;	
		} catch (JAXBException e) {
			Logger.getLogger(SecurityProcessor.class.getName()).log(Level.SEVERE, null, e);
		}
		return null;
	}
	
	private void writePOM(Model model, String path) {
		try {
			String schema = "/co/edu/uniandes/estampala/resources/maven-4.0.0.xsd";
			
			JAXBContext context = JAXBContext.newInstance(Model.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schema);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(model, new FileWriter(path));
		} catch (JAXBException e) {
			Logger.getLogger(SecurityProcessor.class.getName()).log(Level.SEVERE, "Error while preparing to write the JAXB model in: " + path, e);
		} catch (IOException e) {
			Logger.getLogger(SecurityProcessor.class.getName()).log(Level.SEVERE, "Error while writting the JAXB model in: " + path, e);			
		}
	}
	
	private Map<String, String> readTextFile(String path) {
        BufferedReader br = null;
        Map<String, String> featuresTMP = new HashMap<String, String>();
        
        try {        	
            br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
            	line = line.replaceAll("^\"|\"$", "");
                featuresTMP.put(line, line);
            }
        } catch (IOException e) {
        	Logger.getLogger(DiverterService.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
            	Logger.getLogger(DiverterService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return featuresTMP;
	}
	
	private void saveTextFile(String json, String path) {
		BufferedWriter bufferedWriter = null;
		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter(path);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(json);
		} catch (IOException e) {
			Logger.getLogger(DiverterService.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				if (bufferedWriter != null)
					bufferedWriter.close();

				if (fileWriter != null)
					fileWriter.close();

			} catch (IOException ex) {
				Logger.getLogger(DiverterService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	protected void invokeSpoon(String source, String processor) {
		// Invoke spoon processor for methods
		String[] spoonArgs = new String[6];
		spoonArgs[0] = "-i";
		spoonArgs[1] = source;
		spoonArgs[2] = "-p";
		spoonArgs[3] = processor;
		spoonArgs[4] = "--compliance";
		spoonArgs[5] = "7";
		// spoonArgs[6] = "-o";
		// spoonArgs[7] = "./src/main/java";
		try {
			Launcher.main(spoonArgs);
		} catch (Exception e) {
			System.err.println("Error while executing spoon launcher "
					+ e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}
}
