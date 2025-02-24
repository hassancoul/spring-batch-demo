import csv
import random
from datetime import datetime, timedelta

# Fonction pour générer une date aléatoire dans une plage donnée
def random_date(start, end):
    return start + timedelta(
        seconds=random.randint(0, int((end - start).total_seconds()))
    )

# Plage de dates pour les transactions
start_date = datetime(2022, 1, 1)
end_date = datetime(2023, 12, 31)

# Génération des données factices
transactions = []
for i in range(1, 1001):
    date = random_date(start_date, end_date).strftime('%Y-%m-%d %H:%m')
    montant = round(random.uniform(-1000, 1000), 2)
    type_transaction = 'D' if montant < 0 else 'C'
    compte_source = f"FR76{random.randint(1000, 9999)}{random.randint(1000, 9999)}{random.randint(1000, 9999)}{random.randint(1000, 9999)}{random.randint(1000, 9999)}"
    compte_destination = f"FR76{random.randint(1000, 9999)}{random.randint(1000, 9999)}{random.randint(1000, 9999)}{random.randint(1000, 9999)}{random.randint(1000, 9999)}"

    transactions.append([i, date, montant, type_transaction, compte_source, compte_destination])

# Écriture des données dans un fichier CSV
with open('transactions.csv', mode='w', newline='') as file:
    writer = csv.writer(file)
    writer.writerow(['id', 'date', 'montant', 'type', 'compte_source', 'compte_destination'])
    writer.writerows(transactions)

print("Fichier CSV généré avec succès.")
