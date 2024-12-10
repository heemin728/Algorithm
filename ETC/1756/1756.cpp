#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>
using namespace std;

int D,N;
vector<int> ovens;
queue<int> pizza;

int main(){
    cin >> D >> N;

    int mv=987654321;

    for(int i=0;i<D;i++){
        int a;
        cin >> a;
        mv=min(mv,a);
        ovens.push_back(mv);
    }
    for(int i=0;i<N;i++){
        int a;
        cin >> a;
        pizza.push(a);
    }

    int answer=0;

    for(int i=D-1;i >=0;i--){
        int p=pizza.front();

        if(p <= ovens[i]){
            answer=i;
            pizza.pop();
        }
        if(pizza.empty()){
            break;
        }
    }

    if(!pizza.empty()){
        cout << 0 << "\n";
    }
    else{
        cout << answer+1 << "\n";
    }
}