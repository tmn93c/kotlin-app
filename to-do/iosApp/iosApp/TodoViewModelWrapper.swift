//
//  TodoViewModelWrapper.swift
//  iosApp
//
//  Created by Tam Nguyen on 5/12/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import shared

class TodoViewModelWrapper: ObservableObject {
    private let vm: TodoViewModel
    @Published var todos: [TodoItemReponse] = []
    
    init() {
        let factory = DatabaseDriverFactory()
        self.vm = SharedModule().provideViewModel(driverFactory: factory)
        // Start observing
        observeTodos()
        // initial load
        self.loadTodos()
    }
    
    private func observeTodos() {
        vm.observeTodos { [weak self] (list: [TodoItemReponse]?) in
            guard let list = list else { return }
            DispatchQueue.main.async {
                self?.todos = list
            }
        }
    }

    func addTodo(title: String) {
        vm.addTodo(title: title)
        self.loadTodos()
    }

    func removeTodo(id: String) {
        vm.removeTodo(id: id)
        self.loadTodos()
    }
    
    func loadTodos() {
        vm.loadTodos()
    }
}
