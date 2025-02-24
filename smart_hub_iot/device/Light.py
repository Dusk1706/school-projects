from tabulate import tabulate

class Light:
    
    def __init__(self):
        self.name = "Light"
        self.active = False
        self.intensity = 0
        self.color = "FFFFFF"
    
    
    def command(self, command, value):
        if command == "active":
            self.setActive(value)
        elif command == "intensity":
            self.setIntensity(value)
        elif command == "color":
            self.setColor(value)
        else:
            print("Command not found")
          
    
    def print_tabulate(self, command, value):
      print(tabulate([[self.name, value]], 
                      headers=["Device", command], 
                      tablefmt="grid",
                      colalign=("center", "center")))
      
    
    def setActive(self, value):
        if value == "true":
            self.active = True
        else:
            self.active = False
            
        self.print_tabulate("Active", self.active)
     
         
    def setIntensity(self, value):
        self.intensity = int(value)
        self.print_tabulate("Intensity", self.intensity)
        
        
    def setColor(self, value):
        self.color = value
        self.print_tabulate("Color", self.color)
        