from tabulate import tabulate

class Fan:
    
    def __init__(self):
        self.active = False
        self.intensity = 0
    
    
    def command(self, command, value):
        if command == 'state':
            self.setActive(value)
        elif command == 'intensity':
            self.setIntensity(value)
        else:
            print("Command not found")
    
    
    def print_tabulate(self, command, value):
      print(tabulate([[self.name, value]], 
                      headers=["Device", command], 
                      tablefmt="grid",
                      colalign=("center", "center")))
    
    
    def setActive(self, value):
        if value == 'true':
            self.active = True
        elif value == 'off':
            self.active = False
        self.print_tabulate("Active", self.active)
        
        
    def setIntensity(self, intensity):
        self.intensity = intensity
        self.print_tabulate("Intensity", self.intensity)
