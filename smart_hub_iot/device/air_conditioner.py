class AirConditioner:
    
    def __init__(self):
        self.active = False
        self.speed = 0
        self.direction = "front"
        self.temperature = 0
        self.mode = "cool"
    
    
    def command(self, command, value):
        if command == 'active':
            self.setActive(value)
        elif command == 'speed':
            self.setSpeed(value)
        elif command == 'temperature':
            self.setTemperature(value)
        elif command == 'mode':
            self.setMode(value)
        elif command == 'direction':
            self.setDirection(value)
        else:
            print("Command not found")
            

    def setActive(self, value):
        if value == 'true':
            self.active = True
        elif value == 'off':
            self.active = False
        print(f"Active updated: {self.active}")
        
        
    def setSpeed(self, speed):
        self.speed = speed
        print(f"Speed updated: {self.speed}")
        
        
    def setTemperature(self, temperature):
        self.temperature = temperature
        print(f"Temperature updated: {self.temperature}")
        
    
    def setMode(self, mode):
        self.mode = mode
        print(f"Mode updated: {self.mode}")
        
    
    def setDirection(self, direction):
        self.direction = direction
        print(f"Direction updated: {self.direction}")
