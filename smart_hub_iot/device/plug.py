from tabulate import tabulate

class Plug:
    def __init__(self):
       self.active = False


    def command(self, command, value):
        if command == "active":
            self.setActive(value)
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
        