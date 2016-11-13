import Tkinter as tk


guante = tk.Tk()
#titulo de la barra
guante.wm_title("Teclado Digital")
#tamano ventana (anchoxalto)
guante.geometry("400x200")
#ventana no resizabe
guante.resizable(width=False, height=False)


scrollbar = tk.Scrollbar(guante)
scrollbar.pack(side="right", fill="y")

mylist = tk.Listbox(guante, yscrollcommand = scrollbar.set )
for line in range(13):
   mylist.insert("end", " a " + str(line))

mylist.pack( side="left", fill="both")
scrollbar.config( command = mylist.yview )


#Estructura Indice
indice = tk.Label(guante, text="Indice", font=("Times", 16))
indice.grid(row=0, column=0, sticky='se')

indiceT = tk.Entry(guante, text="Indice", font=("Times", 16))
indiceT.grid(row=0, column=0, sticky='se')

indice.pack()

#Estructura Corazon
corazon = tk.Label(guante, text='Corazon', font=("Times", 16))
corazon.grid(row=0, column=1, sticky='se')
corazon.pack()

#Estructura Anular
anular = tk.Label(guante, text='Anular', font=("Times", 16))
anular.grid(row=0, column=2, sticky='se')
anular.pack()


desactivar = tk.Checkbutton(guante, text='Desactivar')
desactivar.grid(columnspan=3, sticky='se')
desactivar.pack()


guante.mainloop()
