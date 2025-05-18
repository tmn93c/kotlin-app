import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject var viewModel = TodoViewModelWrapper()
    @State private var text: String = ""

	var body: some View {
            NavigationView {
                VStack {
                    HStack {
                        TextField("Enter todo", text: $text)
                            .textFieldStyle(RoundedBorderTextFieldStyle())
                        Button("Add") {
                            viewModel.addTodo(title: text)
                            text = ""
                        }
                    }.padding()

                    List {
                        ForEach(viewModel.todos, id: \.id) { todo in
                            HStack {
                                Text(todo.title)
                                Spacer()
                                Button(action: {
                                    viewModel.removeTodo(id: todo.id)
                                }) {
                                    Image(systemName: "trash")
                                }
                            }
                        }
                    }
                }
                .navigationTitle("To-Do List")
            }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
